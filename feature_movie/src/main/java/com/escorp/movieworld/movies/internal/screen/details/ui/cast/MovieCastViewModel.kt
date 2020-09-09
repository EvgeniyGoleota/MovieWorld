package com.escorp.movieworld.movies.internal.screen.details.ui.cast

import androidx.lifecycle.LiveData
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.movies.internal.screen.details.domain.GetMovieCastUseCase
import com.escorp.movieworld.core.data.api.dto.movies.Cast
import com.escorp.movieworld.movies.internal.router.MovieRouter
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

internal class MovieCastViewModel @Inject constructor(
    private val getMovieCastUseCase: GetMovieCastUseCase,
    private val router: MovieRouter
) : ViewModelBase() {

    val movieCast: LiveData<List<Cast>>

    private val castSubject = PublishSubject.create<Int>()

    init {
        val stateObservable = createStateObservable()

        movieCast = stateObservable.ofType<State.Loaded>()
            .map {state ->
                state.cast
            }
            .toAutoDisposableLiveData()
    }

    private fun createStateObservable(): Observable<State> {
        return castSubject
            .flatMap { movieId ->
                getMovieCastUseCase(movieId)
                    .subscribeIoObserveMain()
                    .map<State> { State.Loaded(it) }
                    .toObservable()
                    .toRecoverable(State.Error)
                    .startWith(State.LoadingInProgress)
            }
    }

    fun getMovieCast(movieId: Int) {
        castSubject.onNext(movieId)
    }

    fun openActorDetails(movieId: Int, movieName: String) =
        router.actorDetails(movieId, movieName).autoDisposableSubscribe()

    sealed class State {
        class Loaded(val cast: List<Cast>): State()
        object Error: State()
        object LoadingInProgress: State()
    }
}