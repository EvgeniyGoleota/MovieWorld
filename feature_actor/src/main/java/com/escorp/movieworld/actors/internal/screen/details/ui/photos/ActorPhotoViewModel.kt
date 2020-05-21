package com.escorp.movieworld.actors.internal.screen.details.ui.photos

import androidx.lifecycle.LiveData
import com.escorp.movieworld.actors.internal.screen.details.domain.GetPersonPhotosUseCase
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.core.data.api.dto.common.Image
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

internal class ActorPhotoViewModel @Inject constructor(
    private val getPersonPhotosUseCase: GetPersonPhotosUseCase
) : ViewModelBase() {

    val photos: LiveData<List<Image>>

    private val photosSubject = PublishSubject.create<Int>()

    init {
        val stateObservable = createStateObservable()

        photos = stateObservable
            .filter { (it is State.LoadingInProgress).not() }
            .map { state ->
                when (state) {
                    is State.Loaded -> state.photos
                    else -> emptyList()
                }
            }
            .toAutoDisposableLiveData()
    }

    private fun createStateObservable(): Observable<State> {
        return photosSubject
            .flatMap { personId ->
                getPersonPhotosUseCase(personId)
                    .subscribeIoObserveMain()
                    .map<State> {
                        State.Loaded(it)
                    }
                    .toObservable()
                    .toRecoverable(State.Error)
                    .startWith(State.LoadingInProgress)
            }
    }

    fun getPhotos(personId: Int){
        photosSubject.onNext(personId)
    }

    sealed class State {
        class Loaded(val photos: List<Image>): State()
        object Error : State()
        object LoadingInProgress : State()
    }
}