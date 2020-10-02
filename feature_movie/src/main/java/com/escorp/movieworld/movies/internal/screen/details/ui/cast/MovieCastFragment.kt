package com.escorp.movieworld.movies.internal.screen.details.ui.cast

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.escorp.movieworld.core.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.core.ui.base.BaseFragment
import com.escorp.movieworld.core.ui.RecyclerViewOnItemClickListener
import com.escorp.movieworld.core.utils.ID
import com.escorp.movieworld.core.utils.isIdValid
import com.escorp.movieworld.movies.R
import javax.inject.Inject

internal class MovieCastFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : BaseFragment<FragmentRecyclerViewBinding>(
    R.layout.fragment_recycler_view,
    viewModelFactory
) {
    private lateinit var viewModel: MovieCastViewModel

    private val movieCastListAdapter = MovieCastListAdapter()

    private val movieId: Int? by lazy { arguments?.getInt(ID) }

    companion object {
        fun newInstance(movieId: Int, viewModelFactory: ViewModelProvider.Factory) =
            MovieCastFragment(viewModelFactory).apply {
                arguments = Bundle().apply { putInt(ID, movieId) }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
        if (isIdValid(movieId)) {
            viewModel.getMovieCast(movieId!!)
        }
    }

    override fun FragmentRecyclerViewBinding.onViewCreated(view: View, bundle: Bundle?) {
        movieCastListAdapter.onItemClickListener = object :
            RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int, title: String) {
                viewModel.openActorDetails(itemId, title)
            }
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieCastListAdapter
        }

        viewModel.movieCast.observeInViewLiveCycle { list ->
            if (list.isNotEmpty()) {
                list.toMutableList().sort()
                movieCastListAdapter.setItems(list)
            }
        }
    }
}