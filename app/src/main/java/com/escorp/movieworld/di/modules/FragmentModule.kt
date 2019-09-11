package com.escorp.movieworld.di.modules

import com.escorp.movieworld.ui.detailScreen.actorDetail.actorsInfo.ActorInfoFragment
import com.escorp.movieworld.ui.mainScreen.actorsList.ActorsListFragment
import com.escorp.movieworld.ui.mainScreen.moviesList.MoviesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeActorsListFragment(): ActorsListFragment

    @ContributesAndroidInjector
    abstract fun contributeMoviesListFragment(): MoviesListFragment

    @ContributesAndroidInjector
    abstract fun contributeActorDitailFragmenr(): ActorInfoFragment
}