package com.escorp.movieworld.di.modules

import com.escorp.movieworld.ui.fragments.ActorsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeRecyclerViewFragment(): ActorsListFragment
}