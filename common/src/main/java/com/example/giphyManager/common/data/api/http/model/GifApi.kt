package com.example.giphyManager.common.data.api.http.model

import com.example.giphyManager.common.data.api.ApiConstants
import com.example.giphyManager.common.data.api.http.model.responses.SearchGifsResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface GifApi {
    @GET(ApiConstants.GIF_SEARCH_ENDPOINT)
    suspend fun search(
        @Query("api_key") apiKey: String,
        @Query("q") query: String
    ): SearchGifsResponse

    @GET(ApiConstants.GIF_TRENDING_ENDPOINT)
    suspend fun searchTrending(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): SearchGifsResponse
}