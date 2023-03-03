package com.example.giphyManager.list.presentation

sealed class ListFragmentEvent {
    object RequestNextPage : ListFragmentEvent()
    object ResetPagination : ListFragmentEvent()
    data class QueryInput(val query: String) : ListFragmentEvent()
}