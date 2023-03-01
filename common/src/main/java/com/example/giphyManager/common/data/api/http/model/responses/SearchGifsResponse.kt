package com.example.giphyManager.common.data.api.http.model.responses

import com.example.giphyManager.common.data.api.http.model.apiEntity.ApiGif
import com.example.giphyManager.common.data.api.http.model.apiEntity.ApiMeta
import com.example.giphyManager.common.data.api.http.model.apiEntity.ApiPagination
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchGifsResponse(
    @field:Json(name = "data") val gifs: List<ApiGif>,
    @field:Json(name = "pagination") val pagination: ApiPagination,
    @field:Json(name = "meta") val meta: ApiMeta
)
