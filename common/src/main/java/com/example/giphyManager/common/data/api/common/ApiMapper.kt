package com.example.giphyManager.common.data.api.common

internal interface ApiMapper<in From, out To> {
    fun mapToDomain(apiEntity: From): To
}