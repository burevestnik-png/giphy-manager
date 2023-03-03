package com.example.giphyManager.common.data.api.http.model.responses

import com.example.giphyManager.common.data.api.http.model.apiEntity.ApiGif
import com.example.giphyManager.common.data.api.http.model.apiEntity.ApiMeta
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class GetByIdResponse(
    @field:Json(name = "data") val gif: ApiGif,
    @field:Json(name = "meta") val meta: ApiMeta
)