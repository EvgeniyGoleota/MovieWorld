package com.escorp.movieworld.ui.detailScreen.actorDetail.actorsCredits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.escorp.movieworld.databinding.FragmentRecyclerViewBinding
import com.escorp.movieworld.ui.detailScreen.actorDetail.ActorDetailFragmentArgs
import com.escorp.movieworld.ui.detailScreen.actorDetail.ActorDetailFragmentDirections
import com.escorp.movieworld.utils.ID
import com.escorp.movieworld.utils.interfaces.RecyclerViewOnItemClickListener
import com.escorp.movieworld.utils.ViewModelFactory
import com.escorp.movieworld.utils.enums.DetailActivityTag
import com.escorp.movieworld.utils.isIdValid
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

    private val personId: Int? by lazy {arguments?.getInt(ID)}

    companion object {
        fun newInstance(personId: Int) = ActorCreditsFragment().apply {
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
        creditsListAdapter.onClickListener = object : RecyclerViewOnItemClickListener {
            override fun onItemClick(itemId: Int, title: String) {
                findNavController().navigate(ActorDetailFragmentDirections.actionActorDetailToMovieDetail(itemId, title))
            }

        }
        recycler_view.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = creditsListAdapter
        }
    }

    private fun initViewModel() {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ActorCreditsViewModel::class.java)

        if (isIdValid(personId)) {
            viewModel.getPersonsCredits(personId!!).observe(this, Observer { list ->
                if (list.isNotEmpty()) creditsListAdapter.setItems(list)
            })
        }
    }

}