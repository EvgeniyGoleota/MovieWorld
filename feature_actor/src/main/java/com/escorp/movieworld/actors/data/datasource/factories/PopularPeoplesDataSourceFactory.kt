package com.escorp.movieworld.actors.data.datasource.factories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.escorp.movieworld.actors.data.datasource.PopularPeopleDataSource
import com.escorp.movieworld.actors.domain.usecase.GetPopularPeopleUseCase
import com.escorp.movieworld.network.api.dto.actors.Actor
import io.reactivex.disposables.CompositeDisposable

class PopularPeoplesDataSourceFactory(
    private val getPopularPeopleUseCase: GetPopularPeopleUseCase,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Actor>() {

    val sourceLiveData = MutableLiveData<PopularPeopleDataSource>()
    var latestSource: PopularPeopleDataSource? = null

    override fun create(): DataSource<Int, Actor> {
        latestSource =
            PopularPeopleDataSource(
                getPopularPeopleUseCase,
                compositeDisposable
            )
        sourceLiveData.postValue(latestSource)
        return latestSource!!
    }
}