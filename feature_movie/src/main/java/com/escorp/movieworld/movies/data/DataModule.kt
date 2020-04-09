package com.escorp.movieworld.movies.data

import com.escorp.movieworld.movies.dagger.MovieScope
import dagger.Binds
import dagger.Module

@Module
internal interface DataModule {

    @MovieScope
    @Binds
    fun bindsToMovieRepository(impl: MovieRepositoryImpl): MovieRepository
}