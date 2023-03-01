package com.example.giphyManager.common.data.api.http.model.apiEntity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ApiPagination(
    @field:Json(name = "offset") val offset: Int,
    @field:Json(name = "total_count") val totalCount: Int,
    @field:Json(name = "count") val count: Int
)
