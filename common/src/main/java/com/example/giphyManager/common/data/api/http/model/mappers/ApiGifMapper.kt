package com.example.giphyManager.common.data.api.http.model.mappers

import com.example.giphyManager.common.data.api.common.ApiMapper
import com.example.giphyManager.common.data.api.http.model.apiEntity.ApiGif
import com.example.giphyManager.common.domain.model.Gif
import javax.inject.Inject

internal class ApiGifMapper @Inject constructor() : ApiMapper<ApiGif, Gif> {
    override fun mapToDomain(apiEntity: ApiGif): Gif = with(apiEntity) {
        Gif(
            id = id,
            url = url,
            bitlyUrl = bitlyUrl,
            embedUrl = embedUrl,
            userName = userName,
            rating = rating,
            title = title,
        )
    }
}