package com.example.giphyManager.details.presentation

sealed class DetailsFragmentEvent {
    data class GetGif(val id: String) : DetailsFragmentEvent()
}