package com.escorp.movieworld.actors.internal.screen.list.ui

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.escorp.movieworld.actors.internal.router.ActorRouter
import com.escorp.movieworld.actors.internal.screen.list.domain.GetPagedPopularPeopleLiveDataUseCase
import com.escorp.movieworld.core.data.api.dto.actors.Actor
import com.escorp.movieworld.core.ui.base.ViewModelBase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

internal class ActorsListViewModel @Inject constructor(
    getPagedPopularPeopleLiveDataUseCase: GetPagedPopularPeopleLiveDataUseCase,
    compositeDisposable: CompositeDisposable,
    private val router: ActorRouter
) : ViewModelBase(compositeDisposable) {

    val popularPeoplePagedListLiveData: LiveData<PagedList<Actor>> =
        getPagedPopularPeopleLiveDataUseCase(compositeDisposable)

    fun openActorDetails(personId: Int, personName: String) =
        router.actorDetails(personId, personName).autoDisposableSubscribe()
}