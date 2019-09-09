package com.escorp.movieworld.di.modules

import com.escorp.movieworld.ui.actorsScreen.ActorsListFragment
import com.escorp.movieworld.ui.moviesScreen.MoviesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeActorsListFragment(): ActorsListFragment

    @ContributesAndroidInjector
    abstract fun contributeMoviesListFragment(): MoviesListFragment
}