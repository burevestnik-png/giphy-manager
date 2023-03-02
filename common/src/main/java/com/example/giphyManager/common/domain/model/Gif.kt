package com.example.giphyManager.common.domain.model

data class Gif(
    val id: String,
    val url: String,
    val bitlyUrl: String,
    val embedUrl: String,
    val userName: String,
    val rating: String,
    val title: String,
    val image: Image
)
