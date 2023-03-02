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
    override suspend fun requestGetPaginatedChats(
        pageNumber: Int,
        pageSize: Int
    ): Pair<Pagination, List<Gif>> = try {
        val response = gifApi.search(
            apiKey = ApiConstants.API_KEY,
            query = ""
        )

        Pair(
            first = apiPaginationMapper.mapToDomain(response.pagination),
            second = response.gifs.map { apiGifMapper.mapToDomain(it) }
        )
    } catch (exception: HttpException) {
        Timber.e(exception)
        throw exception
    }
}