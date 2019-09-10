package com.escorp.movieworld.ui.moviesScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.models.Movie
import com.escorp.movieworld.data.models.MovieResponse
import com.escorp.movieworld.data.models.Response
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {

    val pagedMoviesListLiveData: LiveData<PagedList<Movie>> by lazy {
        dataRepository.getPagedMovieListLiveData()
    }

    private val compositeDisposable = CompositeDisposable()
    val responseStatus = MutableLiveData<Response>()

    init {
        retrieveTopRatedMovies(1)
    }


    fun retrieveTopRatedMovies(page: Int) {
        dataRepository.getPopularMovies(page)
            .subscribeOn(Schedulers.io())
            .doOnError { error ->
                error.printStackTrace()
                Log.e("MW:::", "Network error while receiving top rated movies: ${error.message}")
            }
            .doOnSubscribe {
                compositeDisposable.add(it)
            }
            .onErrorReturnItem(MovieResponse())
            .map { movieResponse: MovieResponse ->
                if (movieResponse.isSuccessful()) {
                    if (movieResponse.page == 1L) dataRepository.clearMoviesCash()
                    dataRepository.saveMoviesToCash(movieResponse.results)
                    return@map Response(
                        movieResponse.page,
                        movieResponse.totalResults,
                        movieResponse.totalPages,
                        true
                    )
                } else {
                    return@map Response(false)
                }
            }
            .doOnNext { response ->
                responseStatus.postValue(response)
            }
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
