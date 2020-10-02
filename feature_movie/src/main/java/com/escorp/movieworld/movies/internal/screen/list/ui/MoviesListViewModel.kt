package com.escorp.movieworld.movies.internal.screen.list.ui

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.movies.internal.screen.list.domain.GetPagedPopularMoviesLiveDataUseCase
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import com.escorp.movieworld.movies.internal.router.MovieRouter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

internal class MoviesListViewModel @Inject constructor(
    private val getPagedPopularMoviesLiveDataUseCase: GetPagedPopularMoviesLiveDataUseCase,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val router: MovieRouter
) : ViewModelBase(compositeDisposable) {

    fun getPopularMovies(): LiveData<PagedList<Movie>> =
        getPagedPopularMoviesLiveDataUseCase.invoke(compositeDisposable)

    fun openMovieDetail(movieId: Int, movieName: String) =
        router.movieDetails(movieId, movieName).autoDisposableSubscribe()
}
