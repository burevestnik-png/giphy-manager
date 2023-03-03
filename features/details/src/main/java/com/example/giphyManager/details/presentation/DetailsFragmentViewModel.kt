package com.example.giphyManager.details.presentation

import com.example.giphyManager.common.presentation.components.base.BaseViewModel
import com.example.giphyManager.common.presentation.model.EmptyPayload
import com.example.giphyManager.details.domain.model.UiGifMapper
import com.example.giphyManager.details.domain.usecases.GetGif
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(
    private val getGif: GetGif,
    private val uiGifMapper: UiGifMapper,
) : BaseViewModel<EmptyPayload>(EmptyPayload()) {

    private val _effects = MutableSharedFlow<DetailsFragmentViewEffect>()
    val effects: SharedFlow<DetailsFragmentViewEffect> = _effects

    fun onEvent(event: DetailsFragmentEvent) = when (event) {
        is DetailsFragmentEvent.GetGif -> handleGetGif(event)
    }

    private fun handleGetGif(event: DetailsFragmentEvent.GetGif) = launchIORequest {
        showLoader()
        val gif = getGif(event.id)
        _effects.emit(DetailsFragmentViewEffect.ProvideGifInfo(uiGifMapper.mapToView(gif)))
        hideLoader()
    }

    override fun onFailure(throwable: Throwable) {
        Timber.e(throwable)
    }
}