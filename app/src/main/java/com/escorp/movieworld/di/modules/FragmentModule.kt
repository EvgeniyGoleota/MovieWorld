package com.escorp.movieworld.di.modules

import com.escorp.movieworld.ui.ActorsScreen.ActorsListFragment
import com.escorp.movieworld.ui.MoivesScreen.MoviesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeActorsListFragment(): ActorsListFragment

    @ContributesAndroidInjector
    abstract fun contributeMoviesListFragment(): MoviesListFragment
}