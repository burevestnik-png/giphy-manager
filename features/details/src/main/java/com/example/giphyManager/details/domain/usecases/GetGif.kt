package com.example.giphyManager.details.domain.usecases

import com.example.giphyManager.common.domain.model.Gif
import com.example.giphyManager.common.domain.repositories.GifRepository
import javax.inject.Inject

class GetGif @Inject constructor(
    private val gifRepository: GifRepository
) {
    suspend operator fun invoke(id: String): Gif =
        gifRepository.requestGetGifById(id)
}