package com.escorp.movieworld.ui.detailScreen.movie.similar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.escorp.movieworld.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.ui.detailScreen.movie.MovieDetailFragmentDirections
import com.escorp.movieworld.ui.mainScreen.movies.MoviesListAdapter
import com.escorp.movieworld.ui.uiUtils.BaseFragment
import com.escorp.movieworld.utils.ID
import com.escorp.movieworld.ui.uiUtils.RecyclerViewOnItemClickListener
import com.escorp.movieworld.utils.isIdValid
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import javax.inject.Inject

class SimilarMoviesFragment : BaseFragment<SimilarMoviesViewModel, FragmentRecyclerViewBinding>() {

    @Inject
    lateinit var moviesListAdapter: MoviesListAdapter

    private val movieId: Int? by lazy { arguments?.getInt(ID) }

    companion object {
        fun newInstance(movieId: Int) = SimilarMoviesFragment().apply {
            arguments = Bundle().apply { putInt(ID, movieId) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesListAdapter.onItemClickListener = object :
            RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int, title: String) {
                findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailSelf(itemId, title))
            }
        }
        recycler_view.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = moviesListAdapter
        }
    }

    override fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SimilarMoviesViewModel::class.java)

        if (isIdValid(movieId)) {
            viewModel.getSimilarMovies(movieId!!).observe(this, Observer {
                moviesListAdapter.submitList(it)
            })
        }
    }

    override fun initializeView() {}
}