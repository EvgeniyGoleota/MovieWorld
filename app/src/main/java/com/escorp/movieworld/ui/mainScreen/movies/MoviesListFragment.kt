package com.escorp.movieworld.ui.mainScreen.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.escorp.movieworld.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.ui.mainScreen.MainScreenFragmentDirections
import com.escorp.movieworld.ui.uiUtils.BaseFragment
import com.escorp.movieworld.ui.uiUtils.PaginationScrollListener
import com.escorp.movieworld.ui.uiUtils.RecyclerViewOnItemClickListener
import com.escorp.movieworld.utils.isNetworkConnected
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import javax.inject.Inject

class MoviesListFragment : BaseFragment<MoviesListViewModel, FragmentRecyclerViewBinding>() {

    @Inject
    internal lateinit var movieListAdapter: MoviesListAdapter

    private var page = 1
    private var isLoading = false
    private var isLastPage = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initializeView() {
        movieListAdapter.onItemClickListener = object :
            RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int, title: String) {
                if (context != null && isNetworkConnected(context!!)) findNavController().navigate(
                    MainScreenFragmentDirections.actionMainScreenToMovieDetail(itemId, title)
                )
            }
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieListAdapter
            addOnScrollListener(object : PaginationScrollListener(layoutManager) {
                override fun loadMore() {
                    page++
                    isLoading = true
                    viewModel.retrieveTopRatedMovies(page)
                }

                override fun isLastPage() = isLoading

                override fun isLoading() = isLastPage
            })
        }
    }

    override fun initializeViewModel() {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MoviesListViewModel::class.java)
        viewModel.pagedMoviesListLiveData.observe(
            this,
            Observer { movieListAdapter.submitList(it) })

        viewModel.responseStatus.observe(this, Observer { response ->
            isLoading = false
            isLastPage = response.page == response.totalPages
        })
    }
}