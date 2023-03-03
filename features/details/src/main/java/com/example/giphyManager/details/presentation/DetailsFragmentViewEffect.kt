package com.example.giphyManager.details.presentation

import com.example.giphyManager.details.domain.model.UiGif

sealed class DetailsFragmentViewEffect {
    data class ProvideGifInfo(val uiGif: UiGif) : DetailsFragmentViewEffect()
}