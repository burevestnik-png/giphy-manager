package com.example.giphyManager.common.presentation.components.extensions


import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.giphyManager.common.R
import com.example.giphyManager.common.presentation.model.Event
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

fun Fragment.requireAppCompatActivity() = requireActivity() as AppCompatActivity

fun Fragment.handleFailures(event: Event<Throwable>?) {
    val unhandledFailure = event?.getPayloadOrNull() ?: return
    val fallbackMessage = getString(R.string.an_error_occurred)

    val message =
        if (unhandledFailure.message.isNullOrEmpty()) {
            fallbackMessage
        } else {
            unhandledFailure.message!!
        }

    if (message.isNotEmpty()) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }
}

fun Fragment.launchViewModelsFlow(block: suspend () -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) { block() }
    }
}