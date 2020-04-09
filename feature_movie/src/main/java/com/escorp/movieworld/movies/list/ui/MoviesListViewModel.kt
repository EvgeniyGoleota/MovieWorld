package com.escorp.movieworld.movies.list.ui

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.movies.domain.usecases.GetPagedPopularMoviesLiveDataUseCase
import com.escorp.movieworld.network.api.dto.movies.Movie
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(
    private val getPagedPopularMoviesLiveDataUseCase: GetPagedPopularMoviesLiveDataUseCase,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModelBase(compositeDisposable) {

    fun getPopularMovies(): LiveData<PagedList<Movie>> =
        getPagedPopularMoviesLiveDataUseCase.invoke(compositeDisposable)
}
