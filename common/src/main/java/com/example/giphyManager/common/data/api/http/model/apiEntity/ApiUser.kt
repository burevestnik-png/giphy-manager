package com.example.giphyManager.common.data.api.http.model.apiEntity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiUser(
    @field:Json(name = "avatar_url") val avatarUrl: String,
    @field:Json(name = "profile_url") val profileUrl: String,
    @field:Json(name = "username") val username: String,
)
