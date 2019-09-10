package com.escorp.movieworld.ui.actorDetailScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.models.Actor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ActorDetailViewModel @Inject constructor(private  val dataRepository: DataRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val person = MutableLiveData<Actor>()

    fun retrivePesonDetils(personId: Int) {
        dataRepository.getPersonDetails(personId)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                compositeDisposable.add(it)
            }
            .doOnError { error ->

            }
            .doOnNext { actor ->
                person.postValue(actor)
            }
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}