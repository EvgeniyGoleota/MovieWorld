package com.escorp.movieworld.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.escorp.movieworld.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.ui.adapters.MoviesListAdapter
import com.escorp.movieworld.ui.viewmodel.MoviesListViewModel
import com.paginate.Paginate
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import javax.inject.Inject

class MoviesListFragment : Fragment(), Paginate.Callbacks {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    internal lateinit var movieListAdapter: MoviesListAdapter

    private lateinit var binding: FragmentRecyclerViewBinding
    private lateinit var viewModel: MoviesListViewModel

    private var page = 1
    private var isLoading = false
    private var hasLoadedAllItems = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        initializeViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        recycler_view.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = movieListAdapter
        }
        Paginate.with(recycler_view, this).build()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesListViewModel::class.java)

        viewModel.pagedMoviesListLiveData.observe(this, Observer {
            movieListAdapter.submitList(it)
        })
    }

    override fun onLoadMore() {
        if (!isLoading) {
            isLoading = true
            viewModel.retrieveTopRatedMovies(page++).observe(this, Observer { response ->
                isLoading = false
                if (response.isSuccessful) {
                    hasLoadedAllItems = response.page == response.totalPages
                }
            })
        }
    }

    override fun isLoading() = isLoading

    override fun hasLoadedAllItems() = hasLoadedAllItems
}