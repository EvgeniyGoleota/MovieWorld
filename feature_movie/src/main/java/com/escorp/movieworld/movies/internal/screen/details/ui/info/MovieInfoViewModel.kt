package com.escorp.movieworld.movies.internal.screen.details.ui.info

import androidx.lifecycle.LiveData
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.movies.internal.screen.details.domain.GetMovieDetailsUseCase
import com.escorp.movieworld.core.data.api.dto.movies.MovieDetail
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

internal class MovieInfoViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModelBase() {

    val movie: LiveData<MovieDetail>

    private val detailsSubject = PublishSubject.create<Int>()

    init {
        val stateObservable = createStateObservable()

        movie = stateObservable.ofType<State.Loaded>()
            .map { state ->
                state.details
            }
            .toAutoDisposableLiveData()
    }

    private fun createStateObservable(): Observable<State> {
        return detailsSubject
            .flatMap { movieId ->
                getMovieDetailsUseCase(movieId)
                    .subscribeIoObserveMain()
                    .map<State>{ State.Loaded(it) }
                    .toObservable()
                    .toRecoverable(State.Error)
                    .startWith(State.LoadingInProgress)
            }
    }

    fun getMovieDetails(movieId: Int) {
       detailsSubject.onNext(movieId)
    }

    sealed class State {
        class Loaded(val details: MovieDetail): State()
        object Error : State()
        object LoadingInProgress : State()
    }
}