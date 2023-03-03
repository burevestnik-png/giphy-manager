package com.example.giphyManager.details.presentation

import com.example.giphyManager.common.presentation.components.base.BaseViewModel
import com.example.giphyManager.common.presentation.model.EmptyPayload
import com.example.giphyManager.details.domain.usecases.GetGif
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(
    private val getGif: GetGif
) : BaseViewModel<EmptyPayload>(EmptyPayload()) {

    fun onEvent(event: DetailsFragmentEvent) = when (event) {
        is DetailsFragmentEvent.GetGif -> handleGetGif(event)
    }

    private fun handleGetGif(event: DetailsFragmentEvent.GetGif) = launchIORequest {
        showLoader()
        val gif = getGif(event.id)
        Timber.d(gif.toString())
    }


    override fun onFailure(throwable: Throwable) {
        Timber.e(throwable)
    }
}