package com.escorp.movieworld.ui.detailScreen.actorDetail.actorsCredits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.escorp.movieworld.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.ui.detailScreen.DetailActivity
import com.escorp.movieworld.ui.interfaces.RecyclerViewOnItemClickListener
import com.escorp.movieworld.utils.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import javax.inject.Inject

class ActorCreditsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var creditsListAdapter: ActorCreditsListAdapter

    private lateinit var viewModel: ActorCreditsViewModel
    private lateinit var binding: FragmentRecyclerViewBinding

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
        creditsListAdapter.onClickListener = object : RecyclerViewOnItemClickListener {
            override fun OnItemClick(itemId: Long) {
                //todo implement
            }

        }
        recycler_view.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = creditsListAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ActorCreditsViewModel::class.java)

        viewModel.getPersonsCredits((activity as DetailActivity).id!!).observe(this, Observer { response ->
            if (response.isValid()) creditsListAdapter.setItems(response.cast)
        })
    }

}