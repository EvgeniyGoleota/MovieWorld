package com.escorp.movieworld.ui.detailScreen.movie.cast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.escorp.movieworld.core.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.ui.detailScreen.movie.MovieDetailFragmentDirections
import com.escorp.movieworld.ui.uiUtils.BaseFragment
import com.escorp.movieworld.utils.ID
import com.escorp.movieworld.ui.uiUtils.RecyclerViewOnItemClickListener
import com.escorp.movieworld.utils.isIdValid
import javax.inject.Inject

class MovieCastFragment : BaseFragment<MovieCastViewModel, FragmentRecyclerViewBinding>() {

    @Inject
    lateinit var movieCastListAdapter: MovieCastListAdapter

    private val movieId: Int? by lazy { arguments?.getInt(ID) }

    companion object {
        fun newInstance(movieId: Int) = MovieCastFragment().apply {
            arguments = Bundle().apply { putInt(ID, movieId) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.recyclerView
    }

    override  fun FragmentRecyclerViewBinding.initializeView() {
        movieCastListAdapter.onItemClickListener = object :
            RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int, title: String) {
                findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailToActorDetail(itemId, title))
            }
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieCastListAdapter
        }
    }

    override fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieCastViewModel::class.java)

        if (isIdValid(movieId)) {
            viewModel.getMovieCast(movieId!!).observe(this, Observer { list ->
                if (list.isNotEmpty()) {
                    list.toMutableList().sort()
                    movieCastListAdapter.setItems(list)
                }
            })
        }
    }
}