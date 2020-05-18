package com.escorp.movieworld.actors.internal.screen.details.ui.credits

import androidx.lifecycle.LiveData
import com.escorp.movieworld.actors.internal.screen.details.domain.GetPersonCreditsUseCase
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import javax.inject.Inject

internal class ActorCreditsViewModel @Inject constructor(
    private val getPersonCreditsUseCase: GetPersonCreditsUseCase
) : ViewModelBase() {

    fun getPersonsCredits(personId: Int): LiveData<List<Movie>> =
        getPersonCreditsUseCase.invoke(personId)
            .subscribeIoObserveMain()
            .toObservable()
            .toRecoverable()
            .toAutoDisposableLiveData()
}