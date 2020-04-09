package com.escorp.movieworld.actors.details.ui.info

import android.os.Bundle
import android.view.View
import com.escorp.movieworld.actors.R
import com.escorp.movieworld.actors.databinding.FragmentActorInfoBinding
import com.escorp.movieworld.core.dagger.viewmodel.ViewModelFactory
import com.escorp.movieworld.core.ui.base.BaseFragment
import com.escorp.movieworld.core.utils.ID
import com.escorp.movieworld.core.utils.isIdValid
import javax.inject.Inject

internal class ActorInfoFragment @Inject constructor(
    viewModelFactory: ViewModelFactory
) : BaseFragment<FragmentActorInfoBinding>(
    R.layout.fragment_actor_info,
    viewModelFactory
) {
    private lateinit var viewModel: ActorInfoViewModel

    private val personId: Int? by lazy { arguments?.getInt(ID) }

    companion object {
        fun newInstance(personId: Int, viewModelFactory: ViewModelFactory) =
            ActorInfoFragment(viewModelFactory).apply {
                arguments = Bundle().apply { putInt(ID, personId) }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
    }

    override fun FragmentActorInfoBinding.onViewCreated(view: View, bundle: Bundle?) {
        viewModel = this@ActorInfoFragment.viewModel

        if (isIdValid(personId)) this@ActorInfoFragment.viewModel.retrievePersonDetails(personId!!)
    }
}