package com.escorp.movieworld.ui.detailScreen.movieDetail.movieSimilar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.escorp.movieworld.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.ui.detailScreen.DetailActivity
import com.escorp.movieworld.ui.mainScreen.moviesList.MoviesListAdapter
import com.escorp.movieworld.utils.ViewModelFactory
import com.escorp.movieworld.utils.enums.DetailActivityTag
import com.escorp.movieworld.utils.interfaces.RecyclerViewOnItemClickListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import javax.inject.Inject

class SimilarMoviesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var moviesListAdapter: MoviesListAdapter

    private lateinit var viewModel: SimilarMoviesViewModel
    private lateinit var binding: FragmentRecyclerViewBinding

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
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesListAdapter.onItemClickListener = object : RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int) {
                (activity as DetailActivity).startDetailActivity(DetailActivityTag.MOVIE, itemId)
            }
        }
        recycler_view.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = moviesListAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SimilarMoviesViewModel::class.java)

        viewModel.getSimilarMovies((activity as DetailActivity).id!!).observe(this, Observer {
            moviesListAdapter.submitList(it)
        })
    }
}