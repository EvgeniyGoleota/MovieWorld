package com.escorp.movieworld.actors.details.ui.credits

import androidx.lifecycle.LiveData
import com.escorp.movieworld.actors.domain.usecase.GetPersonCreditsUseCase
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.network.api.dto.movies.Movie
import javax.inject.Inject

class ActorCreditsViewModel @Inject constructor(
    private val getPersonCreditsUseCase: GetPersonCreditsUseCase
) : ViewModelBase() {

    fun getPersonsCredits(personId: Int): LiveData<List<Movie>> =
        getPersonCreditsUseCase.invoke(personId)
            .subscribeIoObserveMain()
            .toObservable()
            .toRecoverable()
            .toAutoDisposableLiveData()
}