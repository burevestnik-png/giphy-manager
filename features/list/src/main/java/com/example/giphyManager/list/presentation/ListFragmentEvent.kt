package com.example.giphyManager.list.presentation

sealed class ListFragmentEvent {
    object RequestNextPage : ListFragmentEvent()
}