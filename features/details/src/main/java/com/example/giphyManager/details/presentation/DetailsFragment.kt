package com.example.giphyManager.details.presentation

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.giphyManager.common.presentation.components.base.BaseFragment
import com.example.giphyManager.common.presentation.components.extensions.handleFailures
import com.example.giphyManager.common.presentation.components.extensions.launchViewModelsFlow
import com.example.giphyManager.common.presentation.model.EmptyPayload
import com.example.giphyManager.common.presentation.model.UIState
import com.example.giphyManager.details.R
import com.example.giphyManager.details.databinding.FragmentDetailsBinding
import com.example.giphyManager.details.domain.model.UiGif
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

private const val GIF_ID = "id"

@AndroidEntryPoint
class DetailsFragment :
    BaseFragment<DetailsFragmentViewModel, FragmentDetailsBinding>(R.layout.fragment_details) {

    override val viewModel: DetailsFragmentViewModel by viewModels()
    override val binding: FragmentDetailsBinding by viewBinding(FragmentDetailsBinding::bind)

    private var gifId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { gifId = it.getString(GIF_ID) }
    }

    override fun processInitialRequests() {
        requestGetGif(gifId!!)
    }

    ///////////////////////////////////////////////////////////////////////////
    // OBSERVING EFFECTS
    ///////////////////////////////////////////////////////////////////////////

    override fun observeViewEffects() {
        launchViewModelsFlow { viewModel.effects.collect { reactTo(it) } }
    }

    private fun reactTo(effect: DetailsFragmentViewEffect) {
        when (effect) {
            is DetailsFragmentViewEffect.ProvideGifInfo -> handleProvideGifInfo(effect.uiGif)
        }
    }

    private fun handleProvideGifInfo(uiGif: UiGif) {
        binding.apply {
            Glide
                .with(requireContext())
                .asGif()
                .load(uiGif.gifUrl)
                .into(gif)

            title.text = uiGif.title
            gifUrl.text = uiGif.gifPageUrl
            rating.text = uiGif.rating
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // STATE OBSERVING
    ///////////////////////////////////////////////////////////////////////////

    override fun observeViewState() {
        launchViewModelsFlow { viewModel.state.collect { updateScreenState(it) } }
    }

    private fun updateScreenState(state: UIState<EmptyPayload>) {
        binding.progressBar.isVisible = state.loading
        handleFailures(state.failure)
    }


    ///////////////////////////////////////////////////////////////////////////
    // ON EVENT WRAPPERS
    ///////////////////////////////////////////////////////////////////////////

    private fun requestGetGif(id: String) {
        viewModel.onEvent(DetailsFragmentEvent.GetGif(id))
    }
}