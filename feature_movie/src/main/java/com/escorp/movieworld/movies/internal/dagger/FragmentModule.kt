package com.escorp.movieworld.movies.internal.dagger

import androidx.fragment.app.Fragment
import com.escorp.movieworld.core.dagger.key.FragmentKey
import com.escorp.movieworld.movies.internal.screen.details.ui.MovieDetailFragment
import com.escorp.movieworld.movies.internal.screen.details.ui.cast.MovieCastFragment
import com.escorp.movieworld.movies.internal.screen.details.ui.info.MovieInfoFragment
import com.escorp.movieworld.movies.internal.screen.details.ui.similar.SimilarMoviesFragment
import com.escorp.movieworld.movies.internal.screen.list.ui.MovieListFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module()
internal interface FragmentModule {

    @MovieScope
    @Binds
    @IntoMap
    @FragmentKey(MovieListFragment::class)
    fun bindMovieListFragment(fragment: MovieListFragment): Fragment

    @MovieScope
    @Binds
    @IntoMap
    @FragmentKey(MovieCastFragment::class)
    fun bindMovieCastFragment(fragment: MovieCastFragment): Fragment

    @MovieScope
    @Binds
    @IntoMap
    @FragmentKey(MovieInfoFragment::class)
    fun bindMovieInfoFragment(fragment: MovieInfoFragment): Fragment

    @MovieScope
    @Binds
    @IntoMap
    @FragmentKey(SimilarMoviesFragment::class)
    fun bindSimilarMoviesFragment(fragment: SimilarMoviesFragment): Fragment

    @MovieScope
    @Binds
    @IntoMap
    @FragmentKey(MovieDetailFragment::class)
    fun bindMovieDetailFragment(fragment: MovieDetailFragment): Fragment
}