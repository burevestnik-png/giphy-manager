package com.example.giphyManager.details.presentation

import android.os.Bundle
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.giphyManager.common.presentation.components.base.BaseFragment
import com.example.giphyManager.details.R
import com.example.giphyManager.details.databinding.FragmentDetailsBinding
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

    private fun requestGetGif(id: String) {
        viewModel.onEvent(DetailsFragmentEvent.GetGif(id))
    }
}