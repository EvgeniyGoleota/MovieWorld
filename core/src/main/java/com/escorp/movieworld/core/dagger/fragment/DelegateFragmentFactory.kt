package com.escorp.movieworld.core.dagger.fragment

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Provider

class DelegateFragmentFactory(
    private val providers: List<Provider<FragmentFactory>>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        Log.i("DelegateFragmentFactory", "DelegateFragmentFactory.instantiate: $className")

        var fragment: Fragment? = null

        for (provider in providers) {
            val instantiateResult = kotlin.runCatching {
                fragment = provider.get().instantiate(classLoader, className)
            }
            if (instantiateResult.isSuccess) {
                break
            }
        }

        return fragment ?: super.instantiate(classLoader, className)
    }
}