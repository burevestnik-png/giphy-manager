package com.example.giphyManager.common.presentation.components.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphyManager.common.presentation.components.extensions.createExceptionHandler
import com.example.giphyManager.common.presentation.model.Event
import com.example.giphyManager.common.presentation.model.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<T>(payload: T) : ViewModel() {

    protected val _state = MutableStateFlow(UIState(payload))
    val state: StateFlow<UIState<T>> = _state

    protected val payload
        get() = state.value.payload

    protected fun modifyState(
        loading: Boolean = _state.value.loading,
        failure: Event<Throwable>? = _state.value.failure,
        copyPayload: (T) -> T = { it }
    ) {
        _state.value =
            state.value.copy(loading = loading, failure = failure, copyPayload = copyPayload)
    }

    protected fun showLoader() {
        _state.value = state.value.copy(loading = true)
    }

    protected fun hideLoader() {
        _state.value = state.value.copy(loading = false)
    }

    protected fun launchIORequest(
        coroutineContext: CoroutineContext = Dispatchers.IO,
        errorMessage: String = "Failed to make request",
        request: suspend () -> Unit
    ): Job {
        val exceptionHandler = viewModelScope.createExceptionHandler(errorMessage) { onFailure(it) }
        return viewModelScope.launch(exceptionHandler + coroutineContext) { request() }
    }

    protected fun withLoading(block: () -> Unit) {
        showLoader()
        block()
        hideLoader()
    }

    protected abstract fun onFailure(throwable: Throwable)
}