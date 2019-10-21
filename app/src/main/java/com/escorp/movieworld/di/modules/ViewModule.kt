package com.escorp.movieworld.di.modules

import com.escorp.movieworld.ui.detailScreen.actor.credits.ActorCreditsListAdapter
import com.escorp.movieworld.ui.detailScreen.actor.photos.ActorPhotoListAdapter
import com.escorp.movieworld.ui.detailScreen.movie.cast.MovieCastListAdapter
import com.escorp.movieworld.ui.mainScreen.actors.ActorsListAdapter
import com.escorp.movieworld.ui.mainScreen.movies.MoviesListAdapter
import dagger.Module
import dagger.Provides

@Module
class ViewModule {

    @Provides
    fun providesActorsListAdapter(): ActorsListAdapter = ActorsListAdapter()

    @Provides
    fun providesMoviesListAdapter(): MoviesListAdapter = MoviesListAdapter()

    @Provides
    fun providesActorPhotoListAdapter(): ActorPhotoListAdapter = ActorPhotoListAdapter()

    @Provides
    fun providesActorCreditsListAdapter(): ActorCreditsListAdapter = ActorCreditsListAdapter()

    @Provides
    fun providesMovieCastListAdapter(): MovieCastListAdapter = MovieCastListAdapter()
}