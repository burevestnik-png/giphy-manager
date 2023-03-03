package com.example.giphyManager.common.data.api.http.model.mappers

import com.example.giphyManager.common.data.api.common.ApiMapper
import com.example.giphyManager.common.data.api.http.model.apiEntity.ApiUser
import com.example.giphyManager.common.domain.model.User
import javax.inject.Inject

internal class ApiUserMapper @Inject constructor() : ApiMapper<ApiUser, User> {
    override fun mapToDomain(apiEntity: ApiUser): User = with(apiEntity) {
        User(
            avatarUrl,
            profileUrl,
            username
        )
    }
}