package com.escorp.movieworld.di.modules

import com.escorp.movieworld.ui.detailScreen.actor.credits.ActorCreditsFragment
import com.escorp.movieworld.ui.detailScreen.actor.info.ActorInfoFragment
import com.escorp.movieworld.ui.detailScreen.actor.photos.ActorPhotoFragment
import com.escorp.movieworld.ui.detailScreen.movie.cast.MovieCastFragment
import com.escorp.movieworld.ui.detailScreen.movie.info.MovieInfoFragment
import com.escorp.movieworld.ui.detailScreen.movie.similar.SimilarMoviesFragment
import com.escorp.movieworld.ui.mainScreen.actors.ActorsListFragment
import com.escorp.movieworld.ui.mainScreen.movies.MoviesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeActorsListFragment(): ActorsListFragment

    @ContributesAndroidInjector
    abstract fun contributeMoviesListFragment(): MoviesListFragment

    @ContributesAndroidInjector
    abstract fun contributeActorInfoFragment(): ActorInfoFragment

    @ContributesAndroidInjector
    abstract fun contributeActorPhotosFragment(): ActorPhotoFragment

    @ContributesAndroidInjector
    abstract fun contributeActorCreditsFragment(): ActorCreditsFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieInfoFragment(): MovieInfoFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieCastFragment(): MovieCastFragment

    @ContributesAndroidInjector
    abstract fun contributeSimilarMoviesFragment(): SimilarMoviesFragment
}