package com.escorp.movieworld.actors.internal.screen.list.ui

import com.escorp.movieworld.actors.internal.screen.list.domain.GetPagedPopularPeopleLiveDataUseCase
import com.escorp.movieworld.core.ui.base.ViewModelBase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

internal class ActorsListViewModel @Inject constructor(
    getPagedPopularPeopleLiveDataUseCase: GetPagedPopularPeopleLiveDataUseCase,
    compositeDisposable: CompositeDisposable
) : ViewModelBase(compositeDisposable) {

    val popularPeoplePagedListLiveData = getPagedPopularPeopleLiveDataUseCase.invoke(compositeDisposable)
}