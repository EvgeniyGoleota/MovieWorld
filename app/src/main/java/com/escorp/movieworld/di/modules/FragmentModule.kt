package com.escorp.movieworld.di.modules

import com.escorp.movieworld.ui.detailScreen.actorDetail.actorsInfo.ActorInfoFragment
import com.escorp.movieworld.ui.detailScreen.actorDetail.actorsCredits.ActorCreditsFragment
import com.escorp.movieworld.ui.detailScreen.actorDetail.actorsPhotos.ActorPhotoFragment
import com.escorp.movieworld.ui.detailScreen.movieDetail.movieCast.MovieCastFragment
import com.escorp.movieworld.ui.detailScreen.movieDetail.movieInfo.MovieInfoFragment
import com.escorp.movieworld.ui.detailScreen.movieDetail.movieSimilar.SimilarMoviesFragment
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