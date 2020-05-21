package com.escorp.movieworld.actors.internal.screen.details.ui.info

import androidx.lifecycle.LiveData
import com.escorp.movieworld.actors.internal.screen.details.domain.GetPersonDetailsUseCase
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.core.data.api.dto.actors.ActorDetail
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

internal class ActorInfoViewModel @Inject constructor(
    private val getPersonDetailsUseCase: GetPersonDetailsUseCase
) : ViewModelBase() {

    val person: LiveData<ActorDetail>

    private val personInfoSubject = PublishSubject.create<Int>()

    init {
        val stateObservable = createStateObservable()

//        stateObservable.ofType<State.Error>()
//            .flatMapCompletable { /*Close the screen, back or something else*/ }
//            .autoDisposableSubscribe()

        person = stateObservable.ofType<State.Loaded>()
            .map { it.personDetails }
            .toAutoDisposableLiveData()
    }

    fun retrievePersonDetails(personId: Int) {
        personInfoSubject.onNext(personId)
    }

    private fun createStateObservable(): Observable<State> {
        return personInfoSubject
            .flatMap { personId ->
                getPersonDetailsUseCase(personId)
                    .subscribeIoObserveMain()
                    .map<State> {
                        State.Loaded(it)
                    }
                    .toObservable()
                    .toRecoverable(State.Error)
                    .startWith(State.LoadingInProgress)
            }
    }

    sealed class State {
        data class Loaded(val personDetails: ActorDetail) : State()
        object Error : State()
        object LoadingInProgress : State()
    }
}