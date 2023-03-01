package com.example.giphyManager.list.presentation

import com.example.giphyManager.list.domain.model.UiGif


data class ListFragmentPayload(
    val noMoreGifsAnymore: Boolean = false,
    val gifs: List<UiGif> = emptyList()
)