package com.example.giphyManager.common.domain.repositories

import com.example.giphyManager.common.domain.model.Gif
import com.example.giphyManager.common.domain.model.Pagination

interface GifRepository {
    suspend fun requestSearchPaginatedChats(
        limit: Int,
        offset: Int,
        query: String
    ): Pair<Pagination, List<Gif>>

    suspend fun requestGetTrendingPaginatedChats(
        limit: Int,
        offset: Int,
    ): Pair<Pagination, List<Gif>>

    suspend fun requestGetGifById(id: String): Gif
}