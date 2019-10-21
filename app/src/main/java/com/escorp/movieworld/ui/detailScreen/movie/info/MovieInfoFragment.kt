package com.escorp.movieworld.ui.detailScreen.movie.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.escorp.movieworld.databinding.FragmentMovieInfoBinding
import com.escorp.movieworld.ui.uiUtils.BaseFragment
import com.escorp.movieworld.utils.ID
import com.escorp.movieworld.utils.isIdValid

class MovieInfoFragment : BaseFragment<MovieInfoViewModel, FragmentMovieInfoBinding>() {

    private val movieId: Int? by lazy { arguments?.getInt(ID) }

    companion object {
        fun newInstance(movieId: Int) = MovieInfoFragment().apply {
            arguments = Bundle().apply { putInt(ID, movieId) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieInfoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieInfoViewModel::class.java)

        if (isIdValid(movieId)) viewModel.getMovieDetails(movieId!!)
    }

    override fun initializeView() {}
}