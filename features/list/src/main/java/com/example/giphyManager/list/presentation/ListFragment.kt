package com.example.giphyManager.list.presentation

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.giphyManager.common.presentation.components.base.BaseFragment
import com.example.giphyManager.list.R
import com.example.giphyManager.list.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment :
    BaseFragment<ListFragmentViewModel, FragmentListBinding>(R.layout.fragment_list) {

    override val binding: FragmentListBinding by viewBinding(FragmentListBinding::bind)
    override val viewModel: ListFragmentViewModel by viewModels()

    override fun processInitialRequests() {
        viewModel.onEvent(ListFragmentEvent.RequestNextPage)
    }
}