package com.escorp.movieworld.actors.list.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.escorp.movieworld.actors.R
import com.escorp.movieworld.core.dagger.viewmodel.ViewModelFactory
import com.escorp.movieworld.core.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.core.ui.base.BaseFragment
import com.escorp.movieworld.core.ui.RecyclerViewOnItemClickListener
import javax.inject.Inject

internal class ActorListFragment @Inject constructor(
    viewModelFactory: ViewModelFactory
) : BaseFragment<FragmentRecyclerViewBinding>(
    R.layout.fragment_recycler_view,
    viewModelFactory
) {

    private lateinit var viewModel: ActorsListViewModel

    internal val actorListAdapter = ActorsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
    }

    override fun FragmentRecyclerViewBinding.onViewCreated(view: View, bundle: Bundle?) {
        actorListAdapter.onItemClickListener = object :
            RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int, title: String) {
                findNavController().navigate(
                    ActorListFragmentDirections.actionActorsListToActorDetail(itemId, title)
                )
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