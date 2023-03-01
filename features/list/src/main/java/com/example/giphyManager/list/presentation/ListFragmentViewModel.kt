package com.example.giphyManager.list.presentation

import com.example.giphyManager.common.domain.model.Pagination
import com.example.giphyManager.common.presentation.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ListFragmentViewModel : BaseViewModel<ListFragmentPayload>(ListFragmentPayload()) {

    companion object {
        const val UI_PAGE_SIZE = Pagination.DEFAULT_PAGE_SIZE

        private const val IS_LAST_PAGE_INITIAL = false
        private const val CURRENT_PAGE_INITIAL = 0
    }

    fun onEvent(event: ListFragmentEvent) = when (event) {
        is ListFragmentEvent.RequestNextPage -> handleRequestingNextPage()
    }

    private fun handleRequestingNextPage() = withLoading {

    }

    override fun onFailure(throwable: Throwable) {
        TODO("Not yet implemented")
    }
}