package com.example.giphyManager.common.data.api.http.model.apiEntity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ApiMeta(
    @field:Json(name = "msg") val message: String,
    @field:Json(name = "status") val status: String,
    @field:Json(name = "response_id") val responseId: String,
)
