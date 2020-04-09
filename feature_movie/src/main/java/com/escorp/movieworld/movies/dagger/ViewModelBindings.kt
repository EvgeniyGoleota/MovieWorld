package com.escorp.movieworld.movies.dagger

import androidx.lifecycle.ViewModel
import com.escorp.movieworld.core.dagger.viewmodel.CoreViewModelModule
import com.escorp.movieworld.core.dagger.viewmodel.ViewModelMapKey
import com.escorp.movieworld.movies.details.ui.cast.MovieCastViewModel
import com.escorp.movieworld.movies.details.ui.info.MovieInfoViewModel
import com.escorp.movieworld.movies.details.ui.similar.SimilarMoviesViewModel
import com.escorp.movieworld.movies.list.ui.MoviesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [CoreViewModelModule::class])
internal interface ViewModelBindings {

    @MovieScope
    @Binds
    @IntoMap
    @ViewModelMapKey(MoviesListViewModel::class)
    fun moviesListViewModel(moviesListViewModel: MoviesListViewModel): ViewModel

    @MovieScope
    @Binds
    @IntoMap
    @ViewModelMapKey(MovieInfoViewModel::class)
    fun movieInfoViewModel(movieInfoViewModel: MovieInfoViewModel): ViewModel

    @MovieScope
    @Binds
    @IntoMap
    @ViewModelMapKey(MovieCastViewModel::class)
    fun movieCastViewModel(movieCastViewModel: MovieCastViewModel): ViewModel

    @MovieScope
    @Binds
    @IntoMap
    @ViewModelMapKey(SimilarMoviesViewModel::class)
    fun similarMoviesViewModel(similarMoviesViewModel: SimilarMoviesViewModel): ViewModel
}