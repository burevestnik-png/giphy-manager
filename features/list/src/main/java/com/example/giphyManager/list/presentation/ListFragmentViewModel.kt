package com.example.giphyManager.list.presentation

import com.example.giphyManager.common.domain.model.Pagination
import com.example.giphyManager.common.presentation.components.base.BaseViewModel
import com.example.giphyManager.list.domain.model.mappers.UiGifMapper
import com.example.giphyManager.list.domain.usecases.RequestNextGifPage
import com.example.giphyManager.list.domain.usecases.RequestNextTrendingGifPage
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListFragmentViewModel @Inject constructor(
    private val uiGifMapper: UiGifMapper,
    private val requestNextGifPage: RequestNextGifPage,
    private val requestNextTrendingGifPage: RequestNextTrendingGifPage,
) :
    BaseViewModel<ListFragmentPayload>(ListFragmentPayload()) {

    companion object {
        const val UI_PAGE_SIZE = Pagination.DEFAULT_PAGE_SIZE
    }

    var isLastPage = false
        private set

    fun onEvent(event: ListFragmentEvent) = when (event) {
        is ListFragmentEvent.RequestNextPage -> handleRequestingNextPage()
    }

    private fun handleRequestingNextPage() = withLoading {
        launchIORequest {
            val (pagination, gifs) = requestNextTrendingGifPage(25, 0)
            modifyState { payload -> payload.copy(gifs = gifs.map { uiGifMapper.mapToView(it) }) }
        }
    }

    override fun onFailure(throwable: Throwable) {
        Timber.e(throwable)
    }
}