package com.escorp.movieworld.di.modules

import com.escorp.movieworld.ui.actorsScreen.ActorsListAdapter
import com.escorp.movieworld.ui.moviesScreen.MoviesListAdapter
import dagger.Module
import dagger.Provides

@Module
class ViewModule {

    @Provides
    fun providesActorsListAdapter(): ActorsListAdapter =
        ActorsListAdapter()

    @Provides
    fun providesMoviesListAdapter(): MoviesListAdapter =
        MoviesListAdapter()
}