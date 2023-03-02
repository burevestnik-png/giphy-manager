package com.example.giphyManager.list.domain.usecases

import com.example.giphyManager.common.domain.model.Pagination
import com.example.giphyManager.common.domain.repositories.GifRepository
import com.example.giphyManager.list.domain.model.UiGif
import com.example.giphyManager.list.domain.model.mappers.UiGifMapper
import javax.inject.Inject

class RequestNextTrendingGifPage @Inject constructor(
    private val uiGifMapper: UiGifMapper,
    private val gifRepository: GifRepository,
) {
    suspend operator fun invoke(
        limit: Int,
        offset: Int,
    ): Pair<Pagination, List<UiGif>> {
        val (pagination, gifs) = gifRepository.requestGetTrendingPaginatedChats(
            limit,
            offset,
        )

        return Pair(
            first = pagination,
            second = gifs.map { uiGifMapper.mapToView(it) }
        )
    }
}