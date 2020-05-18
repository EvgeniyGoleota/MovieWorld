package com.escorp.movieworld.core.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.escorp.movieworld.core.internal.dagger.factory.ViewModelFactory
import com.escorp.movieworld.core.ui.feature.recoverable.Recoverable
import com.escorp.movieworld.core.ui.feature.recoverable.RecoverableDialogFragment
import com.escorp.movieworld.core.ui.feature.recoverable.RecoverableViewModel
import com.escorp.movieworld.core.ui.utils.IOnBackPressed

abstract class BaseFragment<VIEW_DATA_BINDING : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
    protected val viewModelFactory: ViewModelProvider.Factory
) : Fragment(), IOnBackPressed {

    protected val recoverableViewModel: RecoverableViewModel
        get() = RecoverableViewModel.get(this, viewModelFactory)

    private var viewDataBinding: VIEW_DATA_BINDING? = null

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<VIEW_DATA_BINDING>(inflater, layoutResId, container, false)
            .run {
                viewDataBinding = this
                lifecycleOwner = this@BaseFragment
                viewDataBinding?.onViewCreated(root, savedInstanceState)
                root
            }
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recoverableViewModel.unrecoveredThrowableLiveData.observeInViewLiveCycle { throwable ->
            RecoverableDialogFragment.show(this, throwable)
        }
    }

    final override fun onStart() {
        super.onStart()
        viewDataBinding?.onStart()
    }

    final override fun onResume() {
        super.onResume()
        viewDataBinding?.onResume()
    }

    final override fun onPause() {
        super.onPause()
        viewDataBinding?.onPause()
    }

    final override fun onStop() {
        super.onStop()
        viewDataBinding?.onStop()
    }

    /*final*/ override fun onDestroyView() {
        viewDataBinding?.onViewDestroyed()
        viewDataBinding = null
        super.onDestroyView()
    }

    final override fun onBackPressed(): Boolean = viewDataBinding?.onBackPressed() ?: true

    protected open fun VIEW_DATA_BINDING.onViewCreated(view: View, bundle: Bundle?) = Unit
    protected open fun VIEW_DATA_BINDING.onStart() = Unit
    protected open fun VIEW_DATA_BINDING.onResume() = Unit
    protected open fun VIEW_DATA_BINDING.onPause() = Unit
    protected open fun VIEW_DATA_BINDING.onStop() = Unit
    protected open fun VIEW_DATA_BINDING.onViewDestroyed() = Unit
    protected open fun VIEW_DATA_BINDING.onBackPressed(): Boolean = true

    protected fun <T> LiveData<T>.observeInViewLiveCycle(block: (T) -> Unit) {
        observe(viewLifecycleOwner, Observer { block(it) })
    }

    protected inline fun <reified VIEW_MODEL : ViewModel> getViewModel(viewModelStoreOwner: ViewModelStoreOwner = this): VIEW_MODEL {
        val viewModelProvider = ViewModelProvider(viewModelStoreOwner, viewModelFactory)
        return viewModelProvider[VIEW_MODEL::class.java].also { viewModel ->
            if (viewModel is Recoverable) {
                recoverableViewModel.addRecoverable(viewModel)
            }
        }
    }
}