package com.escorp.movieworld.core.dagger.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class DaggerFragmentModule {

    @Provides
    fun providesFragmentFactory(
        provides: MutableMap<Class<out Fragment>, Provider<Fragment>>
    ): FragmentFactory = DaggerFragmentFactory(provides)
}