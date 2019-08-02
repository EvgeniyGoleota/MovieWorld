package com.escorp.movieworld.di.modules

import com.escorp.movieworld.ui.adapters.ActorsListAdapter
import com.escorp.movieworld.ui.fragments.ActorsListFragment
import com.escorp.movieworld.ui.fragments.MoviesListFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeActorsListFragment(): ActorsListFragment

    @ContributesAndroidInjector
    abstract fun contributeMoviesListFragment(): MoviesListFragment
}