package com.escorp.movieworld.movies.internal.dagger

import androidx.lifecycle.ViewModel
import com.escorp.movieworld.core.dagger.key.ViewModelKey
import com.escorp.movieworld.movies.internal.screen.details.ui.cast.MovieCastViewModel
import com.escorp.movieworld.movies.internal.screen.details.ui.info.MovieInfoViewModel
import com.escorp.movieworld.movies.internal.screen.details.ui.similar.SimilarMoviesViewModel
import com.escorp.movieworld.movies.internal.screen.list.ui.MoviesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module()
internal interface ViewModelModule {

    @MovieScope
    @Binds
    @IntoMap
    @ViewModelKey(MoviesListViewModel::class)
    fun moviesListViewModel(moviesListViewModel: MoviesListViewModel): ViewModel

    @MovieScope
    @Binds
    @IntoMap
    @ViewModelKey(MovieInfoViewModel::class)
    fun movieInfoViewModel(movieInfoViewModel: MovieInfoViewModel): ViewModel

    @MovieScope
    @Binds
    @IntoMap
    @ViewModelKey(MovieCastViewModel::class)
    fun movieCastViewModel(movieCastViewModel: MovieCastViewModel): ViewModel

    @MovieScope
    @Binds
    @IntoMap
    @ViewModelKey(SimilarMoviesViewModel::class)
    fun similarMoviesViewModel(similarMoviesViewModel: SimilarMoviesViewModel): ViewModel
}