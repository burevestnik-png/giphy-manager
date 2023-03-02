package com.example.giphyManager.list.domain.usecases

import com.example.giphyManager.common.domain.model.Gif
import com.example.giphyManager.common.domain.model.Pagination
import com.example.giphyManager.common.domain.repositories.GifRepository
import javax.inject.Inject

class RequestNextTrendingGifPage @Inject constructor(
    private val gifRepository: GifRepository,
) {
    suspend operator fun invoke(
        limit: Int,
        offset: Int,
    ): Pair<Pagination, List<Gif>> =
        gifRepository.requestGetTrendingPaginatedChats(
            limit,
            offset,
        )
}