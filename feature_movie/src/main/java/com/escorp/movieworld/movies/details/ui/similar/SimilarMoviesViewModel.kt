package com.escorp.movieworld.movies.details.ui.similar

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.movies.domain.usecases.GetPagedSimilarMoviesLiveDataUseCase
import com.escorp.movieworld.movies.domain.usecases.GetSimilarMoviesUseCase
import com.escorp.movieworld.network.api.dto.movies.Movie
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SimilarMoviesViewModel @Inject constructor(
    private val getPagedSimilarMoviesLiveDataUseCase: GetPagedSimilarMoviesLiveDataUseCase,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModelBase(compositeDisposable) {

    fun getSimilarMovies(movieId: Int): LiveData<PagedList<Movie>> =
        getPagedSimilarMoviesLiveDataUseCase(movieId, compositeDisposable)
}