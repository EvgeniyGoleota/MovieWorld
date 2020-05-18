package com.escorp.movieworld.core.internal.dagger

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.escorp.movieworld.core.internal.dagger.factory.DaggerFragmentFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
internal class FragmentFactoryModule {

    @Provides
    fun providesFragmentFactory(
        provides: MutableMap<Class<out Fragment>, Provider<Fragment>>
    ): FragmentFactory =
        DaggerFragmentFactory(
            provides
        )
}