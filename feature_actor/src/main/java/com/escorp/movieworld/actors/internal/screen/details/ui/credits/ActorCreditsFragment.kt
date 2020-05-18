package com.escorp.movieworld.actors.internal.screen.details.ui.credits

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.escorp.movieworld.actors.R
import com.escorp.movieworld.core.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.core.ui.base.BaseFragment
import com.escorp.movieworld.core.ui.RecyclerViewOnItemClickListener
import com.escorp.movieworld.core.utils.ID
import com.escorp.movieworld.core.utils.isIdValid
import javax.inject.Inject

internal class ActorCreditsFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : BaseFragment<FragmentRecyclerViewBinding>(
    R.layout.fragment_recycler_view,
    viewModelFactory
) {

    private lateinit var viewModel: ActorCreditsViewModel

    private val creditsListAdapter = ActorCreditsListAdapter()

    private val personId: Int? by lazy { arguments?.getInt(ID) }

    companion object {
        fun newInstance(personId: Int, viewModelFactory: ViewModelProvider.Factory) =
            ActorCreditsFragment(viewModelFactory).apply {
                arguments = Bundle().apply { putInt(ID, personId) }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
    }

    override fun FragmentRecyclerViewBinding.onViewCreated(view: View, bundle: Bundle?) {
        creditsListAdapter.onClickListener = object :
            RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int, title: String) {
                val uri = Uri.parse("movieworld://movies/$itemId/$title")
                findNavController().navigate(uri)
            }

        }
        recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = creditsListAdapter
        }

        if (isIdValid(personId)) {
            viewModel.getPersonsCredits(personId!!).observeInViewLiveCycle { list ->
                if (list.isNotEmpty()) creditsListAdapter.setItems(list)
            }
        }
    }
}