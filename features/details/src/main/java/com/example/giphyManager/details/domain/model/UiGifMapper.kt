package com.example.giphyManager.details.domain.model

import com.example.giphyManager.common.domain.model.Gif
import com.example.giphyManager.common.presentation.model.UiMapper
import javax.inject.Inject

class UiGifMapper @Inject constructor() : UiMapper<Gif, UiGif> {
    override fun mapToView(model: Gif): UiGif = with(model) {
        UiGif(
            id = id,
            gifUrl = image.url,
            title = title,
            gifPageUrl = bitlyUrl,
            rating = rating,
            username = userName
        )
    }
}