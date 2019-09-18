package com.escorp.movieworld.ui.mainScreen.actorsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.escorp.movieworld.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.ui.mainScreen.MainActivity
import com.escorp.movieworld.utils.interfaces.RecyclerViewOnItemClickListener
import com.escorp.movieworld.utils.PaginationScrollListener
import com.escorp.movieworld.utils.enums.DetailActivityTag
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import javax.inject.Inject

class ActorsListFragment : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    internal lateinit var actorListAdapter: ActorsListAdapter

    private lateinit var binding: FragmentRecyclerViewBinding
    private lateinit var viewModel: ActorsListViewModel

    private var page = 1
    private var isLoading = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        initializeViewModel()
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
        initializeView()
    }

    private fun initializeView() {
        actorListAdapter.onItemClickListener = object : RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int) {
                (activity as MainActivity).startDetailActivity(DetailActivityTag.ACTOR, itemId)
            }
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = actorListAdapter
            addOnScrollListener(object : PaginationScrollListener(layoutManager) {
                override fun loadMore() {
                    page++
                    isLoading = true
                    viewModel.retrievePopularPeople(page)
                }

                override fun isLastPage() = isLoading

                override fun isLoading() = isLastPage
            })
        }
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ActorsListViewModel::class.java)

        viewModel.pagedActorsListLiveData.observe(this, Observer { actorListAdapter.submitList(it) })

        viewModel.responseStatus.observe(this, Observer { response ->
            isLoading = false
            if (response.isSuccessful) isLastPage = response.page == response.totalPages
        })
    }
}