package com.escorp.movieworld.actors.internal.screen.list.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.escorp.movieworld.actors.R
import com.escorp.movieworld.core.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.core.ui.base.BaseFragment
import com.escorp.movieworld.core.ui.RecyclerViewOnItemClickListener
import javax.inject.Inject

internal class ActorListFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : BaseFragment<FragmentRecyclerViewBinding>(
    R.layout.fragment_recycler_view,
    viewModelFactory
) {

    private lateinit var viewModel: ActorsListViewModel

    private val actorListAdapter = ActorsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
    }

    override fun FragmentRecyclerViewBinding.onViewCreated(view: View, bundle: Bundle?) {
        actorListAdapter.onItemClickListener = object :
            RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int, title: String) {
                viewModel.openActorDetails(itemId, title)
            }
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = actorListAdapter
        }

        viewModel.popularPeoplePagedListLiveData.observeInViewLiveCycle {
            actorListAdapter.submitList(it)
        }
    }
}