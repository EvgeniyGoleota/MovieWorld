package com.escorp.movieworld.ui.ActorsScreen

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.api.models.Actor
import com.escorp.movieworld.data.api.models.ActorResponse
import com.escorp.movieworld.utils.NetworkState
import io.reactivex.FlowableSubscriber
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription
import javax.inject.Inject

class ActorsDataSource @Inject constructor(private val dataRepository: DataRepository) : PageKeyedDataSource<Long, Actor>() {

    private val networkState = MutableLiveData<NetworkState>()
    private val initialLoading = MutableLiveData<NetworkState>()

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Actor>
    ) {
        initialLoading.postValue(NetworkState.LOADING)
        networkState.postValue(NetworkState.LOADING)

        dataRepository.getPopularPeopleFlowable(1)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Actor>) {

    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Actor>) {

    }

    fun getNetworkState() = networkState

    fun getInitialLoading() = initialLoading
}