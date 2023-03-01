package com.example.giphyManager.common.data.api.http.model.apiEntity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiGif(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "bitly_url") val bitlyUrl: String,
    @field:Json(name = "embed_url") val embedUrl: String,
    @field:Json(name = "username") val userName: String,
    @field:Json(name = "rating") val rating: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "alt_text") val altText: String
)
