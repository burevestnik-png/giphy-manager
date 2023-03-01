package com.example.giphyManager.common.data.api.http.model

import com.example.giphyManager.common.data.api.ApiConstants
import com.example.giphyManager.common.data.api.http.model.responses.SearchGifsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApi {
    @GET(ApiConstants.GIF_SEARCH_ENDPOINT)
    suspend fun search(
        @Query("api_key") apiKey: String,
        @Query("q") query: String
    ): SearchGifsResponse
}