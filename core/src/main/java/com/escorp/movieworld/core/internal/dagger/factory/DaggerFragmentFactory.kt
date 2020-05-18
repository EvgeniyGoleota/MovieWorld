package com.escorp.movieworld.core.internal.dagger.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.android.tools.build.jetifier.core.utils.Log
import javax.inject.Provider

internal class DaggerFragmentFactory(
    private val fragmentProviders: MutableMap<Class<out Fragment>, Provider<Fragment>>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        classLoader.loadClass(className).let { fragmentClass ->
            when (val fragmentProvider = fragmentProviders[fragmentClass]) {
                null -> super.instantiate(classLoader, className)
                else -> fragmentProvider.get()
            }
        }
}