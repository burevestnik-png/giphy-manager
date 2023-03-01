package com.example.giphyManager.common.domain.model

data class Pagination(
    val offset: Int,
    val totalCount: Int,
    val count: Int
) {
    companion object {
        const val DEFAULT_PAGE_SIZE = 25
    }
}
