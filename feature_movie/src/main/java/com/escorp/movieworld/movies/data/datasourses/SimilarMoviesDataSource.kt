package com.escorp.movieworld.movies.data.datasourses

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.escorp.movieworld.movies.domain.usecases.GetSimilarMoviesUseCase
import com.escorp.movieworld.network.api.dto.movies.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SimilarMoviesDataSource(
    private val movieId: Int,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        val currentPage = 1

        getSimilarMoviesUseCase(movieId, currentPage)
            .doOnSubscribe {
                compositeDisposable.addAll(it)
            }
            .doOnError { error ->
                error.printStackTrace()
                Log.e("MW:::", "Network error while receiving similar movies: ${error.message}")
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { list ->
                callback.onResult(list, null, currentPage.inc())
            }
            .subscribe()
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        val currentPage = params.key

        getSimilarMoviesUseCase(movieId, currentPage)
            .doOnSubscribe {
                compositeDisposable.addAll(it)
            }
            .doOnError { error ->
                error.printStackTrace()
                Log.e("MW:::", "Network error while receiving similar movies: ${error.message}")
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { list ->
                callback.onResult(list, currentPage.inc())
            }
            .subscribe()
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {}
}