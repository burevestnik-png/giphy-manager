package com.example.giphyManager.list.domain.usecases

import com.example.giphyManager.common.domain.model.Gif
import com.example.giphyManager.common.domain.model.Pagination
import com.example.giphyManager.common.domain.model.exceptions.NoMoreItemsException
import com.example.giphyManager.common.domain.repositories.GifRepository
import javax.inject.Inject

class RequestNextGifPage @Inject constructor(
    private val gifRepository: GifRepository,
) {
    suspend operator fun invoke(
        limit: Int,
        offset: Int,
        query: String
    ): Pair<Pagination, List<Gif>> =
        gifRepository.requestSearchPaginatedChats(
            limit, offset, query
        ).also {
            if (it.second.isEmpty()) {
               throw NoMoreItemsException()
            }
        }
}