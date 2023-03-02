package com.example.giphyManager.list.domain.usecases

import com.example.giphyManager.common.domain.model.Gif
import com.example.giphyManager.common.domain.model.Pagination
import com.example.giphyManager.common.domain.repositories.GifRepository
import javax.inject.Inject

class RequestNextGifPage @Inject constructor(
    private val gifRepository: GifRepository,
) {
    suspend operator fun invoke(): Pair<Pagination, List<Gif>> =
        gifRepository.requestSearchPaginatedChats(
            pageNumber = 1,
            pageSize = 3
        )
}