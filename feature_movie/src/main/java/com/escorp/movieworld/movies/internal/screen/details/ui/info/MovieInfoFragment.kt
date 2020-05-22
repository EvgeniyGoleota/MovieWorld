package com.escorp.movieworld.movies.internal.screen.details.ui.info

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.escorp.movieworld.core.ui.base.BaseFragment
import com.escorp.movieworld.core.utils.ID
import com.escorp.movieworld.core.utils.isIdValid
import com.escorp.movieworld.movies.R
import com.escorp.movieworld.movies.databinding.FragmentMovieInfoBinding
import javax.inject.Inject

internal class MovieInfoFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : BaseFragment<FragmentMovieInfoBinding>(
    R.layout.fragment_movie_info,
    viewModelFactory
) {
    private lateinit var viewModel: MovieInfoViewModel

    private val movieId: Int? by lazy { arguments?.getInt(ID) }

    companion object {
        fun newInstance(movieId: Int, viewModelFactory: ViewModelProvider.Factory) = MovieInfoFragment(viewModelFactory).apply {
            arguments = Bundle().apply { putInt(ID, movieId) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
    }

    override fun FragmentMovieInfoBinding.onViewCreated(view: View, bundle: Bundle?) {
        viewModel = this@MovieInfoFragment.viewModel
        lifecycleOwner = this@MovieInfoFragment
        if (isIdValid(movieId)) this@MovieInfoFragment.viewModel.getMovieDetails(movieId!!)
    }
}