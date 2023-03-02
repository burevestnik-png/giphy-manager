package com.example.giphyManager.common.data.api.http.model.mappers

import com.example.giphyManager.common.data.api.common.ApiMapper
import com.example.giphyManager.common.data.api.http.model.apiEntity.ApiImages
import com.example.giphyManager.common.domain.model.Image
import javax.inject.Inject

internal class ApiImageMapper @Inject constructor() : ApiMapper<ApiImages, Image> {
    override fun mapToDomain(apiEntity: ApiImages): Image = with(apiEntity.image) {
        Image(
            url,
            width,
            height,
            size
        )
    }
}