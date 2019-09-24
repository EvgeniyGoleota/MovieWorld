package com.escorp.movieworld.ui.detailScreen.actorDetail.actorsInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.escorp.movieworld.databinding.FragmentActorInfoBinding
import com.escorp.movieworld.ui.detailScreen.actorDetail.ActorDetailFragmentArgs
import com.escorp.movieworld.utils.ID
import com.escorp.movieworld.utils.ViewModelFactory
import com.escorp.movieworld.utils.isIdValid
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ActorInfoFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    lateinit var binding: FragmentActorInfoBinding
    lateinit var viewModel: ActorInfoViewModel

    private val personId: Int? by lazy { arguments?.getInt(ID) }

    companion object {
        fun newInstance(personId: Int) = ActorInfoFragment().apply {
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
        binding = FragmentActorInfoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ActorInfoViewModel::class.java)

         if (isIdValid(personId)) viewModel.retrievePersonDetails(personId!!)
    }
}