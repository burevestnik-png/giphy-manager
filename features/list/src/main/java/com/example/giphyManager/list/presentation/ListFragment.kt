package com.example.giphyManager.list.presentation

import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.giphyManager.common.presentation.components.base.BaseFragment
import com.example.giphyManager.common.presentation.components.extensions.handleFailures
import com.example.giphyManager.common.presentation.components.extensions.launchViewModelsFlow
import com.example.giphyManager.common.presentation.components.extensions.navigate
import com.example.giphyManager.common.presentation.model.UIState
import com.example.giphyManager.common.presentation.utils.InfiniteScrollListener
import com.example.giphyManager.common.utils.Route
import com.example.giphyManager.common.utils.Routes
import com.example.giphyManager.list.R
import com.example.giphyManager.list.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ListFragment :
    BaseFragment<ListFragmentViewModel, FragmentListBinding>(R.layout.fragment_list) {

    override val binding: FragmentListBinding by viewBinding(FragmentListBinding::bind)
    override val viewModel: ListFragmentViewModel by viewModels()

    private lateinit var adapter: GifAdapter

    override fun processInitialRequests() {
        viewModel.onEvent(ListFragmentEvent.RequestNextPage)
    }

    ///////////////////////////////////////////////////////////////////////////
    // SETUPING UI
    ///////////////////////////////////////////////////////////////////////////

    override fun setupUI() {
        adapter = setupGifAdapter()
        setupRecyclerView(adapter)

        listenToListPulling()
        setupSearchViewListener()
    }

    private fun listenToListPulling() {
        binding.swipeLayout.setOnRefreshListener { requestResetPagination() }
    }

    private fun setupGifAdapter() = GifAdapter(onClickListener = {
        navigate(Route.build {
            screen = Routes.DETAILS
            addQueryParam("id", it)
        })
    })

    private fun setupRecyclerView(adapter: GifAdapter) {
        binding.recyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
            addOnScrollListener(createOnScrollListener(layoutManager as LinearLayoutManager))
        }
    }

    private fun createOnScrollListener(
        layoutManager: LinearLayoutManager,
    ): RecyclerView.OnScrollListener {
        return object : InfiniteScrollListener(layoutManager, ListFragmentViewModel.UI_PAGE_SIZE) {
            override fun loadMoreItems() = requestNextPage()
            override fun isLastPage(): Boolean = viewModel.isLastPage
            override fun isLoading(): Boolean = viewModel.state.value.loading
        }
    }

    private fun setupSearchViewListener() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                requestNewQueryInput(query.orEmpty())
                binding.search.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                requestNewQueryInput(newText.orEmpty())
                return true
            }
        })
    }

    ///////////////////////////////////////////////////////////////////////////
    // STATE OBSERVING
    ///////////////////////////////////////////////////////////////////////////

    override fun observeViewState() {
        launchViewModelsFlow { viewModel.state.collect { updateScreenState(it) } }
    }

    private fun updateScreenState(state: UIState<ListFragmentPayload>) {
        Timber.d("updateScreenState: ${state.loading}")

        binding.swipeLayout.isRefreshing = state.loading
        adapter.updateGifs(state.payload.gifs)

        handleFailures(state.failure)
    }

    ///////////////////////////////////////////////////////////////////////////
    // ON EVENT WRAPPERS
    ///////////////////////////////////////////////////////////////////////////

    private fun requestNextPage() {
        viewModel.onEvent(ListFragmentEvent.RequestNextPage)
    }

    private fun requestResetPagination() {
        viewModel.onEvent(ListFragmentEvent.ResetPagination)
    }

    private fun requestNewQueryInput(query: String) {
        viewModel.onEvent(ListFragmentEvent.QueryInput(query))
    }

}