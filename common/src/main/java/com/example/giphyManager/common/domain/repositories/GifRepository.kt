package com.example.giphyManager.common.domain.repositories

import com.example.giphyManager.common.domain.model.Gif
import com.example.giphyManager.common.domain.model.Pagination

interface GifRepository {
    suspend fun requestGetPaginatedChats(
        pageNumber: Int,
        pageSize: Int
    ): Pair<Pagination, List<Gif>>
}