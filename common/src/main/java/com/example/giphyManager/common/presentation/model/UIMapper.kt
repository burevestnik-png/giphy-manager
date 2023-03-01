package com.example.giphyManager.common.presentation.model

interface UiMapper<E, V> {
    fun mapToView(model: E): V
}