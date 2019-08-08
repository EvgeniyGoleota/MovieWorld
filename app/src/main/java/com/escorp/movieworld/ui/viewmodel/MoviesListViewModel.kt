package com.escorp.movieworld.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.api.models.Movie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val pagedMoviesListLiveData: LiveData<PagedList<Movie>> by lazy {
        dataRepository.getPagedMovieListLiveData()
    }

    fun retrieveTopRatedMovies(page: Int) = dataRepository.getTopRatedMovies(page)


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
