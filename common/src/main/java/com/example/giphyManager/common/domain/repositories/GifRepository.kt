package com.example.giphyManager.common.domain.repositories

import com.example.giphyManager.common.domain.model.Gif
import com.example.giphyManager.common.domain.model.Pagination

interface GifRepository {
    suspend fun requestSearchPaginatedChats(
        pageNumber: Int,
        pageSize: Int
    ): Pair<Pagination, List<Gif>>

    suspend fun requestGetTrendingPaginatedChats(
        limit: Int,
        offset: Int,
    ): Pair<Pagination, List<Gif>>
}