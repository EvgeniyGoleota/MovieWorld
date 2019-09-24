package com.escorp.movieworld.ui.detailScreen.movieDetail.movieInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.escorp.movieworld.databinding.FragmentMovieInfoBinding
import com.escorp.movieworld.ui.detailScreen.movieDetail.MovieDetailFragmentArgs
import com.escorp.movieworld.utils.ID
import com.escorp.movieworld.utils.ViewModelFactory
import com.escorp.movieworld.utils.isIdValid
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MovieInfoFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MovieInfoViewModel
    private lateinit var binding: FragmentMovieInfoBinding

    private val movieId: Int? by lazy { arguments?.getInt(ID) }

    companion object {
        fun newInstance(movieId: Int) = MovieInfoFragment().apply {
            arguments = Bundle().apply { putInt(ID, movieId) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        initViewModel()
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


    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieInfoViewModel::class.java)

        if (isIdValid(movieId)) viewModel.getMovieDetails(movieId!!)
    }
}