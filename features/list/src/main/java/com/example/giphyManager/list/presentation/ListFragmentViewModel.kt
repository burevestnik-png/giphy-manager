package com.example.giphyManager.list.presentation

import com.example.giphyManager.common.domain.model.Gif
import com.example.giphyManager.common.domain.model.Pagination
import com.example.giphyManager.common.presentation.components.base.BaseViewModel
import com.example.giphyManager.list.domain.model.mappers.UiGifMapper
import com.example.giphyManager.list.domain.usecases.RequestNextGifPage
import com.example.giphyManager.list.domain.usecases.RequestNextTrendingGifPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListFragmentViewModel @Inject constructor(
    private val uiGifMapper: UiGifMapper,
    private val requestNextGifPage: RequestNextGifPage,
    private val requestNextTrendingGifPage: RequestNextTrendingGifPage,
) : BaseViewModel<ListFragmentPayload>(ListFragmentPayload()) {

    companion object {
        const val UI_PAGE_SIZE = Pagination.DEFAULT_PAGE_SIZE

        const val INITIAL_CURRENT_PAGE = 0
        const val INITIAL_IS_LAST_PAGE = false
    }

    private var currentPage = INITIAL_CURRENT_PAGE
    private var currentPageSize = UI_PAGE_SIZE

    var isLastPage = INITIAL_IS_LAST_PAGE
        private set

    private var remoteSearchJob: Job = Job()
    private var query: String = ""

    fun onEvent(event: ListFragmentEvent) = when (event) {
        is ListFragmentEvent.RequestNextPage -> handleRequestingNextPage()
        is ListFragmentEvent.ResetPagination -> handleResetPagination()
        is ListFragmentEvent.QueryInput ->  handleNewQueryInput(event)
    }

    private fun handleRequestingNextPage() {
        remoteSearchJob.cancel()
        remoteSearchJob = launchIORequest {
            showLoader()

            val (pagination, gifs) = if (query.isNotEmpty()) {
                requestNextGifPage(
                    limit = currentPageSize,
                    offset = currentPage * currentPageSize,
                    query = query
                )
            } else {
                requestNextTrendingGifPage(
                    limit = currentPageSize,
                    offset = currentPage * currentPageSize
                )
            }

            updatePagingInfo(pagination)
            onNewGifList(gifs)
        }
    }

    private fun onNewGifList(gifs: List<Gif>) {
        val gifsFromServer = gifs.map { uiGifMapper.mapToView(it) }
        val gifsFromServerIds = gifsFromServer.map { it.id }

        val currentGifs = payload.gifs.filter { it.id !in gifsFromServerIds }
        val updatedList = currentGifs + gifsFromServer

        modifyState(loading = false) { payload -> payload.copy(gifs = updatedList) }
    }

    private fun handleNewQueryInput(event: ListFragmentEvent.QueryInput) {
        if (event.query == query) return

        query = event.query
        modifyState { payload -> payload.copy(gifs = emptyList()) }
        handleResetPagination()
    }

    private fun handleResetPagination() {
        resetPaging()
        handleRequestingNextPage()
    }

    private fun resetPaging() {
        currentPage = INITIAL_CURRENT_PAGE
        isLastPage = INITIAL_IS_LAST_PAGE
    }

    private fun updatePagingInfo(pagination: Pagination) {
        currentPage = pagination.offset / pagination.count + 1
        isLastPage = pagination.totalCount - pagination.offset < currentPageSize
    }

    override fun onFailure(throwable: Throwable) {
        Timber.e(throwable)
    }
}