package com.escorp.movieworld.ui.detailScreen.actorDetail.actorsPhotos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.escorp.movieworld.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.ui.detailScreen.actorDetail.ActorDetailFragmentArgs
import com.escorp.movieworld.utils.ID
import com.escorp.movieworld.utils.interfaces.RecyclerViewOnItemClickListener
import com.escorp.movieworld.utils.ViewModelFactory
import com.escorp.movieworld.utils.isIdValid
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import javax.inject.Inject

class ActorPhotoFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var photoListAdapter: ActorPhotoListAdapter

    private lateinit var viewModel: ActorPhotoViewModel
    private lateinit var binding: FragmentRecyclerViewBinding

    private val personId: Int? by lazy { arguments?.getInt(ID) }

    companion object {
        fun newInstance(personId: Int) = ActorPhotoFragment().apply {
            arguments = Bundle().apply { putInt(ID, personId) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        initViewModel()
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
        initView()
    }

    private fun initView() {
        photoListAdapter.onItemClickListener = object : RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int, title: String) {

            }
        }
        recycler_view.apply {
            adapter = photoListAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ActorPhotoViewModel::class.java)

        if (isIdValid(personId)) {
            viewModel.getPhotos(personId!!).observe(this, Observer { response ->
                if (response.isValid()) {
                    photoListAdapter.setItems(response.profiles)
                }
            })
        }
    }
}