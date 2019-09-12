package com.escorp.movieworld.di.modules

import com.escorp.movieworld.ui.detailScreen.actorDetail.actorsPhotos.ActorPhotoListAdapter
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
}