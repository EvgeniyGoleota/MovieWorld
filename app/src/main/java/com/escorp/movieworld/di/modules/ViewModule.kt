package com.escorp.movieworld.di.modules

import com.escorp.movieworld.ui.detailScreen.actorDetail.actorsCredits.ActorCreditsListAdapter
import com.escorp.movieworld.ui.detailScreen.actorDetail.actorsPhotos.ActorPhotoListAdapter
import com.escorp.movieworld.ui.detailScreen.movieDetail.movieCast.MovieCastListAdapter
import com.escorp.movieworld.ui.mainScreen.actorsList.ActorsListAdapter
import com.escorp.movieworld.ui.mainScreen.moviesList.MoviesListAdapter
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