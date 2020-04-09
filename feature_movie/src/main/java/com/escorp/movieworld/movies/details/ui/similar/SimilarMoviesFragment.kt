package com.escorp.movieworld.movies.details.ui.similar

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.escorp.movieworld.core.dagger.viewmodel.ViewModelFactory
import com.escorp.movieworld.core.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.core.ui.base.BaseFragment
import com.escorp.movieworld.core.ui.RecyclerViewOnItemClickListener
import com.escorp.movieworld.core.utils.ID
import com.escorp.movieworld.core.utils.isIdValid
import com.escorp.movieworld.movies.R
import com.escorp.movieworld.movies.details.ui.MovieDetailFragmentDirections
import com.escorp.movieworld.movies.list.ui.MoviesListAdapter
import javax.inject.Inject

internal class SimilarMoviesFragment @Inject constructor(
    viewModelFactory: ViewModelFactory
) : BaseFragment<FragmentRecyclerViewBinding>(
    R.layout.fragment_recycler_view,
    viewModelFactory
) {
    private lateinit var viewModel: SimilarMoviesViewModel

    private val moviesListAdapter = MoviesListAdapter()

    private val movieId: Int? by lazy { arguments?.getInt(ID) }

    companion object {
        fun newInstance(movieId: Int, viewModelFactory: ViewModelFactory) =
            SimilarMoviesFragment(viewModelFactory).apply {
                arguments = Bundle().apply { putInt(ID, movieId) }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
    }

    override fun FragmentRecyclerViewBinding.onViewCreated(view: View, bundle: Bundle?) {
        moviesListAdapter.onItemClickListener = object :
            RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int, title: String) {
                findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailSelf(itemId, title))
            }
        }
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = moviesListAdapter
        }

        if (isIdValid(movieId)) {
            viewModel.getSimilarMovies(movieId!!)
                .observeInViewLiveCycle { moviesListAdapter.submitList(it) }
        }
    }
}