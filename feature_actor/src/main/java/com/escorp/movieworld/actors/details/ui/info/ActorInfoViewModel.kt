package com.escorp.movieworld.actors.details.ui.info

import androidx.databinding.ObservableField
import com.escorp.movieworld.actors.domain.usecase.GetPersonDetailsUseCase
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.network.api.dto.actors.ActorDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ActorInfoViewModel @Inject constructor(
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