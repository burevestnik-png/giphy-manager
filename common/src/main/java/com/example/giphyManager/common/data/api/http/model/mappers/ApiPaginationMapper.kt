package com.example.giphyManager.common.data.api.http.model.mappers

import com.example.giphyManager.common.data.api.common.ApiMapper
import com.example.giphyManager.common.data.api.http.model.apiEntity.ApiPagination
import com.example.giphyManager.common.domain.model.Pagination
import javax.inject.Inject

internal class ApiPaginationMapper @Inject constructor(): ApiMapper<ApiPagination, Pagination> {
    override fun mapToDomain(apiEntity: ApiPagination): Pagination = with(apiEntity) {
        Pagination(
            offset = offset,
            totalCount = totalCount,
            count = count
        )
    }
}