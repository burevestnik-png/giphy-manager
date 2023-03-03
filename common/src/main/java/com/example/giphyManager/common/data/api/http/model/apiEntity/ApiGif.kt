package com.example.giphyManager.common.data.api.http.model.apiEntity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ApiImage(
    @field:Json(name = "url") val url: String,
    @field:Json(name = "width") val width: String,
    @field:Json(name = "height") val height: String,
    @field:Json(name = "size") val size: String,
)

@JsonClass(generateAdapter = true)
internal data class ApiImages(
    @field:Json(name = "fixed_height") val image: ApiImage
)

@JsonClass(generateAdapter = true)
internal data class ApiGif(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "bitly_url") val bitlyUrl: String,
    @field:Json(name = "embed_url") val embedUrl: String,
    @field:Json(name = "username") val userName: String,
    @field:Json(name = "rating") val rating: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "images") val images: ApiImages,
    @field:Json(name = "user") val user: ApiUser
)
