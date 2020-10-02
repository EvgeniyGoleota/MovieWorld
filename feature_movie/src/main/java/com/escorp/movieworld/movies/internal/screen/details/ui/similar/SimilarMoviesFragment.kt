package com.escorp.movieworld.movies.internal.screen.details.ui.similar

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.escorp.movieworld.core.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.core.ui.base.BaseFragment
import com.escorp.movieworld.core.ui.RecyclerViewOnItemClickListener
import com.escorp.movieworld.core.utils.ID
import com.escorp.movieworld.core.utils.isIdValid
import com.escorp.movieworld.movies.R
import com.escorp.movieworld.movies.internal.screen.list.ui.MoviesListAdapter
import javax.inject.Inject

internal class SimilarMoviesFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : BaseFragment<FragmentRecyclerViewBinding>(
    R.layout.fragment_recycler_view,
    viewModelFactory
) {
    private lateinit var viewModel: SimilarMoviesViewModel

    private val moviesListAdapter = MoviesListAdapter()

    private val movieId: Int? by lazy { arguments?.getInt(ID) }

    companion object {
        fun newInstance(movieId: Int, viewModelFactory: ViewModelProvider.Factory) =
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
                viewModel.openMovieDetails(itemId, title)
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