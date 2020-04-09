package com.escorp.movieworld.actors.data.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.escorp.movieworld.actors.domain.usecase.GetPopularPeopleUseCase
import com.escorp.movieworld.network.api.dto.actors.Actor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PopularPeopleDataSource(
    private val getPopularPeopleUseCase: GetPopularPeopleUseCase,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Actor>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Actor>
    ) {
        val currentPage = 1

        getPopularPeopleUseCase(currentPage)
            .doOnSubscribe {
                compositeDisposable.addAll(it)
            }
            .doOnError { error ->
                error.printStackTrace()
                Log.e("MW:::", "Network error while receiving popular peoples: ${error.message}")
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { list ->
                callback.onResult(list, null, currentPage.inc())
            }
            .subscribe()
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Actor>) {
        val currentPage = params.key

        getPopularPeopleUseCase(currentPage)
            .doOnSubscribe {
                compositeDisposable.addAll(it)
            }
            .doOnError { error ->
                error.printStackTrace()
                Log.e("MW:::", "Network error while receiving popular peoples: ${error.message}")
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { list ->
                callback.onResult(list, currentPage.inc())
            }
            .subscribe()
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Actor>) {}
}