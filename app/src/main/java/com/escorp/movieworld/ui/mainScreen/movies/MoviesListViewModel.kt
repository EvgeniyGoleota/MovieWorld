package com.escorp.movieworld.ui.mainScreen.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.models.Movie
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
    val responseStatus = MutableLiveData<Response<Movie>>()

    init {
        retrieveTopRatedMovies(1)
    }


    fun retrieveTopRatedMovies(page: Int) {
        dataRepository.getPopularMovies(page)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                compositeDisposable.add(it)
            }
            .doOnError { error ->
                error.printStackTrace()
                Log.e("MW:::", "Network error while receiving top rated movies: ${error.message}")
            }
            .onErrorReturnItem(Response())
            .filter { it.isSuccessful() }
            .map { movieResponse: Response<Movie> ->
                if (movieResponse.page == 1L) dataRepository.clearMoviesCash()
                dataRepository.saveMoviesToCash(movieResponse.results)
                return@map movieResponse
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
