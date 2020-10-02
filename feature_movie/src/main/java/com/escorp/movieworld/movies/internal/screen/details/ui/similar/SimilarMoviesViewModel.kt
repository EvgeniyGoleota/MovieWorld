package com.escorp.movieworld.movies.internal.screen.details.ui.similar

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.movies.internal.screen.details.domain.GetPagedSimilarMoviesLiveDataUseCase
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import com.escorp.movieworld.movies.internal.router.MovieRouter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

internal class SimilarMoviesViewModel @Inject constructor(
    private val getPagedSimilarMoviesLiveDataUseCase: GetPagedSimilarMoviesLiveDataUseCase,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val router: MovieRouter
) : ViewModelBase(compositeDisposable) {

    fun getSimilarMovies(movieId: Int): LiveData<PagedList<Movie>> =
        getPagedSimilarMoviesLiveDataUseCase(movieId, compositeDisposable)

    fun openMovieDetails(movieId: Int, movieName: String) =
        router.movieDetails(movieId, movieName).autoDisposableSubscribe()
}