package com.escorp.movieworld.actors.list.ui

import com.escorp.movieworld.actors.domain.usecase.GetPagedPopularPeopleLiveDataUseCase
import com.escorp.movieworld.core.ui.base.ViewModelBase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ActorsListViewModel @Inject constructor(
    getPagedPopularPeopleLiveDataUseCase: GetPagedPopularPeopleLiveDataUseCase,
    compositeDisposable: CompositeDisposable
) : ViewModelBase(compositeDisposable) {

    val popularPeoplePagedListLiveData = getPagedPopularPeopleLiveDataUseCase.invoke(compositeDisposable)
}