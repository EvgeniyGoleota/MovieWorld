package com.escorp.movieworld.data.dataSources

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.models.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SimilarMoviesDataSource(
    private val movieId: Int,
    private val dataRepository: DataRepository,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        val currentPage = 1

        dataRepository.getSimilarMovies(movieId, currentPage)
            .doOnSubscribe {
                compositeDisposable.addAll(it)
            }
            .doOnError { error ->
                error.printStackTrace()
                Log.e("MW:::", "Network error while receiving similar movies: ${error.message}")
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { response ->
                callback.onResult(response.results, null, currentPage.inc())
            }
            .subscribe()
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        val currentPage = params.key

        dataRepository.getSimilarMovies(movieId, currentPage)
            .doOnSubscribe {
                compositeDisposable.addAll(it)
            }
            .doOnError { error ->
                error.printStackTrace()
                Log.e("MW:::", "Network error while receiving similar movies: ${error.message}")
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { response ->
                callback.onResult(response.results, currentPage.inc())
            }
            .subscribe()
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {}
}