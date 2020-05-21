package com.escorp.movieworld.actors.internal.screen.details.ui.credits

import androidx.lifecycle.LiveData
import com.escorp.movieworld.actors.internal.screen.details.domain.GetPersonCreditsUseCase
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

internal class ActorCreditsViewModel @Inject constructor(
    private val getPersonCreditsUseCase: GetPersonCreditsUseCase
) : ViewModelBase() {

    val credits: LiveData<List<Movie>>

    private val creditsSubject = PublishSubject.create<Int>()

    init {
        val stateObservable = createPersonCreditsObservable()
        credits = stateObservable
            .filter { (it is State.LoadingInProgress).not() }
            .map { state ->
                when (state) {
                    is State.Loaded -> state.credits
                    else -> emptyList()
                }
            }
            .startWith(emptyList<Movie>())
            .toAutoDisposableLiveData()
    }

    private fun createPersonCreditsObservable(): Observable<State> {
        return creditsSubject.flatMap { personId ->
            getPersonCreditsUseCase.invoke(personId)
                .subscribeIoObserveMain()
                .map<State> {
                    State.Loaded(it)
                }
                .toObservable()
                .toRecoverable(State.Error)
                .startWith(State.LoadingInProgress)
        }
    }

    fun getPersonsCredits(personId: Int) {
        creditsSubject.onNext(personId)
    }

    sealed class State {
        data class Loaded(val credits: List<Movie>): State()
        object Error : State()
        object LoadingInProgress : State()
    }
}