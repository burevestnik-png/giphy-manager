package com.example.giphyManager.common.data.repositories

import com.example.giphyManager.common.data.api.http.model.GifApi
import com.example.giphyManager.common.domain.repositories.GifRepository
import javax.inject.Inject

internal class GifRepositoryImpl @Inject constructor(
    private val gifApi: GifApi
) : GifRepository