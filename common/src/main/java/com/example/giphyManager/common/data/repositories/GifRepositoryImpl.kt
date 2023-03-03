package com.example.giphyManager.common.data.repositories

import com.example.giphyManager.common.data.api.ApiConstants
import com.example.giphyManager.common.data.api.http.model.GifApi
import com.example.giphyManager.common.data.api.http.model.mappers.ApiGifMapper
import com.example.giphyManager.common.data.api.http.model.mappers.ApiPaginationMapper
import com.example.giphyManager.common.domain.model.Gif
import com.example.giphyManager.common.domain.model.Pagination
import com.example.giphyManager.common.domain.repositories.GifRepository
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

internal class GifRepositoryImpl @Inject constructor(
    private val gifApi: GifApi,
    private val apiGifMapper: ApiGifMapper,
    private val apiPaginationMapper: ApiPaginationMapper,
) : GifRepository {
    override suspend fun requestSearchPaginatedChats(
        limit: Int,
        offset: Int,
        query: String
    ): Pair<Pagination, List<Gif>> = runCatching {
        val response = gifApi.search(
            apiKey = ApiConstants.API_KEY,
            limit = limit,
            offset = offset,
            query = query
        )

        Pair(
            first = apiPaginationMapper.mapToDomain(response.pagination),
            second = response.gifs.map { apiGifMapper.mapToDomain(it) }
        )
    }.onFailure {
        Timber.e(it)
    }.getOrThrow()

    override suspend fun requestGetTrendingPaginatedChats(
        limit: Int,
        offset: Int,
    ): Pair<Pagination, List<Gif>> = runCatching {
        val response = gifApi.searchTrending(ApiConstants.API_KEY, limit, offset)

        Pair(
            first = apiPaginationMapper.mapToDomain(response.pagination),
            second = response.gifs.map { apiGifMapper.mapToDomain(it) }
        )
    }.onFailure {
        Timber.e(it)
    }.getOrThrow()

    override suspend fun requestGetGifById(id: Int): Gif = runCatching {
        val response = gifApi.getGifById(id, ApiConstants.API_KEY)
        apiGifMapper.mapToDomain(response.gif)
    }.onFailure {
        Timber.e(it)
    }.getOrThrow()
}