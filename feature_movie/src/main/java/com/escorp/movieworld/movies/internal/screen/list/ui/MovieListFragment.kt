package com.escorp.movieworld.movies.internal.screen.list.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.escorp.movieworld.core.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.core.ui.base.BaseFragment
import com.escorp.movieworld.core.ui.RecyclerViewOnItemClickListener
import com.escorp.movieworld.movies.R
import javax.inject.Inject

internal class MovieListFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : BaseFragment<FragmentRecyclerViewBinding>(
    R.layout.fragment_recycler_view,
    viewModelFactory
) {
    private lateinit var viewModel: MoviesListViewModel

    private val movieListAdapter = MoviesListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
    }

    override fun FragmentRecyclerViewBinding.onViewCreated(view: View, bundle: Bundle?) {
        movieListAdapter.onItemClickListener = object :
            RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int, title: String) {
                viewModel.openMovieDetail(itemId, title)
            }
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieListAdapter
        }

        viewModel.getPopularMovies().observeInViewLiveCycle {
            movieListAdapter.submitList(it)
        }
    }
}