package com.escorp.movieworld.core.dagger.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.android.tools.build.jetifier.core.utils.Log
import javax.inject.Provider

class DaggerFragmentFactory(
    private val provides: MutableMap<Class<out Fragment>, Provider<Fragment>>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        loadFragmentClass(classLoader, className).let { fragmentClass ->
            requireNotNull(provides[fragmentClass]).get()
        }.also {
            Log.i("DaggerFragmentFactory", "DaggerFragmentFactory.instantiate: $className")
        }
}