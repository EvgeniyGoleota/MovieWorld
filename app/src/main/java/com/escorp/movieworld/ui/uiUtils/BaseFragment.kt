package com.escorp.movieworld.ui.uiUtils

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<V : ViewModel, B : ViewDataBinding> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: V
    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initializeView()
    }

    abstract fun initializeViewModel()

    protected open fun B.initializeView() {}
}