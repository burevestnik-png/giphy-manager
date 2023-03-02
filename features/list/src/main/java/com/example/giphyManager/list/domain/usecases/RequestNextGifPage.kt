package com.example.giphyManager.list.domain.usecases

import com.example.giphyManager.common.domain.model.Pagination
import com.example.giphyManager.common.domain.repositories.GifRepository
import com.example.giphyManager.list.domain.model.UiGif
import com.example.giphyManager.list.domain.model.mappers.UiGifMapper
import javax.inject.Inject

class RequestNextGifPage @Inject constructor(
    private val uiGifMapper: UiGifMapper,
    private val gifRepository: GifRepository,
) {
    suspend operator fun invoke(): Pair<Pagination, List<UiGif>> {
        val (pagination, gifs) = gifRepository.requestGetPaginatedChats(
            pageNumber = 1,
            pageSize = 3
        )

        return Pair(
            first = pagination,
            second = gifs.map { uiGifMapper.mapToView(it) }
        )
    }
}