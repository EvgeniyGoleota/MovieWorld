package com.escorp.movieworld.ui.mainScreen.actors

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.models.Actor
import com.escorp.movieworld.data.models.Response
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ActorsListViewModel @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {

    val pagedActorsListLiveData: LiveData<PagedList<Actor>> by lazy {
        dataRepository.getPagedActorListLiveData()
    }
    private val compositeDisposable = CompositeDisposable()
    val responseStatus = MutableLiveData<Response<Actor>>()

    init {
        retrievePopularPeople(1)
    }

    fun retrievePopularPeople(page: Int) {
        dataRepository.getPopularPeople(page)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                compositeDisposable.add(it)
            }
            .doOnError { error ->
                error.printStackTrace()
                Log.e("MW:::", "Network error while receiving popular peoples: ${error.message}")
            }
            .map { actorResponse: Response<Actor> ->
                if (actorResponse.page == 1L) dataRepository.clearActorsCash()
                dataRepository.saveActorsToCash(actorResponse.results)
                return@map actorResponse
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