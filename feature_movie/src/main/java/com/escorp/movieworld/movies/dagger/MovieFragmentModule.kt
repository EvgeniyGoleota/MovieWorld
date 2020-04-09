package com.escorp.movieworld.movies.dagger

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.escorp.movieworld.core.dagger.fragment.DaggerFragmentFactory
import com.escorp.movieworld.core.dagger.fragment.DaggerFragmentModule
import com.escorp.movieworld.core.dagger.fragment.FragmentKey
import com.escorp.movieworld.movies.details.ui.MovieDetailFragment
import com.escorp.movieworld.movies.details.ui.cast.MovieCastFragment
import com.escorp.movieworld.movies.details.ui.info.MovieInfoFragment
import com.escorp.movieworld.movies.details.ui.similar.SimilarMoviesFragment
import com.escorp.movieworld.movies.list.ui.MovieListFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [DaggerFragmentModule::class])
internal interface MovieFragmentModule {

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