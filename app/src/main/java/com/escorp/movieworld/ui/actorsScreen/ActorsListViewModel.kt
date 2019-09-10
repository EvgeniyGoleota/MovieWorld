package com.escorp.movieworld.ui.actorsScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.models.Actor
import com.escorp.movieworld.data.models.ActorResponse
import com.escorp.movieworld.data.models.Response
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ActorsListViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    val pagedActorsListLiveData: LiveData<PagedList<Actor>> by lazy {
        dataRepository.getPagedActorListLiveData()
    }
    private val compositeDisposable = CompositeDisposable()
    val responseStatus = MutableLiveData<Response>()

    init {
        retrievePopularPeople(1)
    }

    fun retrievePopularPeople(page: Int) {
        dataRepository.getPopularPeople(page)
            .subscribeOn(Schedulers.io())
            .doOnError { error ->
                error.printStackTrace()
                Log.e("MW:::", "Network error while receiving popular peoples: ${error.message}")
            }
            .doOnSubscribe {
                compositeDisposable.add(it)
            }
            .onErrorReturnItem(ActorResponse())
            .map { actorResponse: ActorResponse ->
                if (actorResponse.isSuccessful()) {
                    if (actorResponse.page == 1L) dataRepository.clearActorsCash()
                    dataRepository.saveActorsToCash(actorResponse.results)
                    return@map Response(
                        actorResponse.page,
                        actorResponse.totalResults,
                        actorResponse.totalPages,
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