package com.escorp.movieworld.ui.detailScreen.actor.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.escorp.movieworld.core.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.ui.uiUtils.BaseFragment
import com.escorp.movieworld.utils.ID
import com.escorp.movieworld.ui.uiUtils.RecyclerViewOnItemClickListener
import com.escorp.movieworld.utils.isIdValid
import javax.inject.Inject

class ActorPhotoFragment : BaseFragment<ActorPhotoViewModel, FragmentRecyclerViewBinding>() {

    @Inject
    lateinit var photoListAdapter: ActorPhotoListAdapter

    private val personId: Int? by lazy { arguments?.getInt(ID) }

    companion object {
        fun newInstance(personId: Int) = ActorPhotoFragment().apply {
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
        photoListAdapter.onItemClickListener = object :
            RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int, title: String) {

            }
        }
        recyclerView.apply {
            adapter = photoListAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ActorPhotoViewModel::class.java)

        if (isIdValid(personId)) {
            viewModel.getPhotos(personId!!).observe(this, Observer { response ->
                photoListAdapter.setItems(response)
            })
        }
    }
}