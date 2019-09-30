package com.escorp.movieworld.ui.mainScreen.moviesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.escorp.movieworld.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.ui.MainActivity
import com.escorp.movieworld.ui.mainScreen.MainScreenFragmentDirections
import com.escorp.movieworld.utils.PaginationScrollListener
import com.escorp.movieworld.utils.enums.DetailActivityTag
import com.escorp.movieworld.utils.interfaces.RecyclerViewOnItemClickListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import javax.inject.Inject

class MoviesListFragment : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    internal lateinit var movieListAdapter: MoviesListAdapter

    private lateinit var binding: FragmentRecyclerViewBinding
    private lateinit var viewModel: MoviesListViewModel

    private var page = 1
    private var isLoading = false
    private var isLastPage = false

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
        movieListAdapter.onItemClickListener = object : RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int, title: String) {
                findNavController().navigate(MainScreenFragmentDirections.actionMainScreenToMovieDetail(itemId, title))
            }
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieListAdapter
            addOnScrollListener(object : PaginationScrollListener(layoutManager) {
                override fun loadMore() {
                    page++
                    isLoading = true
                    viewModel.retrieveTopRatedMovies(page)
                }

                override fun isLastPage() = isLoading

                override fun isLoading() = isLastPage
            })
        }
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesListViewModel::class.java)
        viewModel.pagedMoviesListLiveData.observe(this, Observer { movieListAdapter.submitList(it) })

        viewModel.responseStatus.observe(this, Observer { response ->
            isLoading = false
            isLastPage = response.page == response.totalPages
        })
    }
}