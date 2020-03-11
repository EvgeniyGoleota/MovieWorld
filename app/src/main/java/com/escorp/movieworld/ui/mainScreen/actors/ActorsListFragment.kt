package com.escorp.movieworld.ui.mainScreen.actors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.escorp.movieworld.core.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.ui.uiUtils.BaseFragment
import com.escorp.movieworld.ui.uiUtils.PaginationScrollListener
import com.escorp.movieworld.ui.uiUtils.RecyclerViewOnItemClickListener
import com.escorp.movieworld.utils.isNetworkConnected
import javax.inject.Inject

class ActorsListFragment : BaseFragment<ActorsListViewModel, FragmentRecyclerViewBinding>() {

    @Inject
    internal lateinit var actorListAdapter: ActorsListAdapter

    private var page = 1
    private var isLoading = false
    private var isLastPage = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun FragmentRecyclerViewBinding.initializeView() {
        actorListAdapter.onItemClickListener = object :
            RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int, title: String) {
                if (context != null && isNetworkConnected(context!!)) findNavController().navigate(ActorsListFragmentDirections.actionActorsToActorDetail(itemId, title))
            }
        }

        recyclerView.apply {
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

    override fun initializeViewModel() {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ActorsListViewModel::class.java)

        viewModel.pagedActorsListLiveData.observe(
            this,
            Observer { actorListAdapter.submitList(it) })

        viewModel.responseStatus.observe(this, Observer { response ->
            isLoading = false
            isLastPage = response.page == response.totalPages
        })
    }
}