package com.escorp.movieworld.actors.internal.screen.details.ui.photos

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.escorp.movieworld.actors.R
import com.escorp.movieworld.core.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.core.ui.base.BaseFragment
import com.escorp.movieworld.core.ui.RecyclerViewOnItemClickListener
import com.escorp.movieworld.core.utils.ID
import com.escorp.movieworld.core.utils.isIdValid
import javax.inject.Inject

internal class ActorPhotoFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : BaseFragment<FragmentRecyclerViewBinding>(
    R.layout.fragment_recycler_view,
    viewModelFactory
) {

    private lateinit var viewModel: ActorPhotoViewModel

    private val photoListAdapter = ActorPhotoListAdapter()

    private val personId: Int? by lazy { arguments?.getInt(ID) }

    companion object {
        fun newInstance(personId: Int, viewModelFactory: ViewModelProvider.Factory) =
            ActorPhotoFragment(viewModelFactory).apply {
                arguments = Bundle().apply { putInt(ID, personId) }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
        if (isIdValid(personId)) {
            viewModel.getPhotos(personId!!)
        }
    }

    override fun FragmentRecyclerViewBinding.onViewCreated(view: View, bundle: Bundle?) {
        photoListAdapter.onItemClickListener = object :
            RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int, title: String) {

            }
        }
        recyclerView.apply {
            adapter = photoListAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        viewModel.photos.observeInViewLiveCycle { response ->
            photoListAdapter.setItems(response)
        }
    }
}