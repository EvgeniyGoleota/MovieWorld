package com.escorp.movieworld.ui.detailScreen.actorDetail.actorsInfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.models.Actor
import com.escorp.movieworld.data.models.ActorDetail
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ActorInfoViewModel @Inject constructor(private  val dataRepository: DataRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val person = MutableLiveData<ActorDetail>()

    fun retrievePersonDetails(personId: Long) {
        dataRepository.getPersonDetails(personId)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                compositeDisposable.add(it)
            }
            .doOnError { error ->
                error.printStackTrace()
            }
            .doOnNext { actorDetail ->
                person.postValue(actorDetail)
            }
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}