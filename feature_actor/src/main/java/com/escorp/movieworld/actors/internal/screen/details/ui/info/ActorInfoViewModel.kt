package com.escorp.movieworld.actors.internal.screen.details.ui.info

import androidx.databinding.ObservableField
import com.escorp.movieworld.actors.internal.screen.details.domain.GetPersonDetailsUseCase
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.core.data.api.dto.actors.ActorDetail
import javax.inject.Inject

internal class ActorInfoViewModel @Inject constructor(
    private val getPersonDetailsUseCase: GetPersonDetailsUseCase
) : ViewModelBase() {

    val person = ObservableField<ActorDetail>()

    fun retrievePersonDetails(personId: Int) {
        getPersonDetailsUseCase(personId)
            .subscribeIoObserveMain()
            .toObservable()
            .toRecoverable()
            .autoDisposableSubscribe(person::set)
    }
}