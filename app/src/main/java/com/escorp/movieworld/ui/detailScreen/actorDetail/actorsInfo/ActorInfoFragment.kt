package com.escorp.movieworld.ui.detailScreen.actorDetail.actorsInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.escorp.movieworld.databinding.FragmentActorInfoBinding
import com.escorp.movieworld.ui.detailScreen.DetailActivity
import com.escorp.movieworld.utils.ID
import com.escorp.movieworld.utils.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ActorInfoFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var binding: FragmentActorInfoBinding
    lateinit var viewModel: ActorInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        initViewModel()
        (activity as DetailActivity).intent.getSerializableExtra(ID)?.let {
            viewModel.retrievePersonDetails(it as Long)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActorInfoBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@ActorInfoFragment
            viewModel = viewModel
        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ActorInfoViewModel::class.java)

    }
}