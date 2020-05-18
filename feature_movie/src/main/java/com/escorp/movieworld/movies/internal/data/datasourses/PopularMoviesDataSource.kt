package com.escorp.movieworld.movies.internal.data.datasourses

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.escorp.movieworld.movies.internal.screen.list.domain.GetPopularMoviesUseCase
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

internal class PopularMoviesDataSource(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        val currentPage = 1

        getPopularMoviesUseCase(currentPage)
            .doOnSubscribe {
                compositeDisposable.addAll(it)
            }
            .doOnError { error ->
                error.printStackTrace()
                Log.e("MW:::", "Network error while receiving popular movies: ${error.message}")
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

        getPopularMoviesUseCase(currentPage)
            .doOnSubscribe {
                compositeDisposable.addAll(it)
            }
            .doOnError { error ->
                error.printStackTrace()
                Log.e("MW:::", "Network error while receiving popular movies: ${error.message}")
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