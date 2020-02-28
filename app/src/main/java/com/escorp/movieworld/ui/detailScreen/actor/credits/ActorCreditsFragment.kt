package com.escorp.movieworld.ui.detailScreen.actor.credits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.escorp.movieworld.core.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.ui.detailScreen.actor.ActorDetailFragmentDirections
import com.escorp.movieworld.ui.uiUtils.BaseFragment
import com.escorp.movieworld.utils.ID
import com.escorp.movieworld.ui.uiUtils.RecyclerViewOnItemClickListener
import com.escorp.movieworld.utils.injectViewModel
import com.escorp.movieworld.utils.isIdValid
import javax.inject.Inject

class ActorCreditsFragment : BaseFragment<ActorCreditsViewModel, FragmentRecyclerViewBinding>() {

    @Inject
    lateinit var creditsListAdapter: ActorCreditsListAdapter

    private val personId: Int? by lazy {arguments?.getInt(ID)}

    companion object {
        fun newInstance(personId: Int) = ActorCreditsFragment().apply {
            arguments = Bundle().apply { putInt(ID, personId) }
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

    override fun FragmentRecyclerViewBinding.initializeView() {
        creditsListAdapter.onClickListener = object :
            RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int, title: String) {
                findNavController().navigate(ActorDetailFragmentDirections.actionActorDetailToMovieDetail(itemId, title))
            }

        }
        recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = creditsListAdapter
        }
    }

    override fun initializeViewModel() {
        viewModel = injectViewModel(viewModelFactory)

        if (isIdValid(personId)) {
            viewModel.getPersonsCredits(personId!!).observe(this, Observer { list ->
                if (list.isNotEmpty()) creditsListAdapter.setItems(list)
            })
        }
    }

}