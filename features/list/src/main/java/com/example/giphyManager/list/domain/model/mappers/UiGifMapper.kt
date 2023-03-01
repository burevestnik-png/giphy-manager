package com.example.giphyManager.list.domain.model.mappers

import com.example.giphyManager.common.domain.model.Gif
import com.example.giphyManager.common.presentation.model.UiMapper
import com.example.giphyManager.list.domain.model.UiGif
import javax.inject.Inject

class UiGifMapper @Inject constructor() : UiMapper<Gif, UiGif> {
    override fun mapToView(model: Gif): UiGif = with(model) {
        UiGif(
            id = id,
            url = url
        )
    }
}