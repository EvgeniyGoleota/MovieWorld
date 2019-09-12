package com.escorp.movieworld.ui.detailScreen.actorDetail.actorsInfo

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.models.Actor
import com.escorp.movieworld.data.models.ActorDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ActorInfoViewModel @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val person = ObservableField<ActorDetail>()
    val name = MutableLiveData<String>()

    fun retrievePersonDetails(personId: Long) {
        dataRepository.getPersonDetails(personId)
            .doOnSubscribe {
                compositeDisposable.add(it)
            }
            .doOnError { error ->
                error.printStackTrace()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { actorDetail ->
                person.set(actorDetail)
                person.notifyChange()
                name.postValue(actorDetail.name)
            }
//            .doOnNext(person::set)
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}