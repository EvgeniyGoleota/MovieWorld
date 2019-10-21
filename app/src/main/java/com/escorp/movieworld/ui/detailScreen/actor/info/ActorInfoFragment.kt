package com.escorp.movieworld.ui.detailScreen.actor.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.escorp.movieworld.databinding.FragmentActorInfoBinding
import com.escorp.movieworld.ui.uiUtils.BaseFragment
import com.escorp.movieworld.utils.ID
import com.escorp.movieworld.utils.isIdValid

class ActorInfoFragment : BaseFragment<ActorInfoViewModel, FragmentActorInfoBinding>() {

    private val personId: Int? by lazy { arguments?.getInt(ID) }

    companion object {
        fun newInstance(personId: Int) = ActorInfoFragment().apply {
            arguments = Bundle().apply { putInt(ID, personId) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActorInfoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ActorInfoViewModel::class.java)

         if (isIdValid(personId)) viewModel.retrievePersonDetails(personId!!)
    }

    override fun initializeView() {}
}