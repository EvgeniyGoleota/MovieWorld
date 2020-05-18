package com.escorp.movieworld.actors.internal.screen.list.domain

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.escorp.movieworld.actors.internal.data.datasource.factories.PopularPeoplesDataSourceFactory
import com.escorp.movieworld.core.utils.defaultPageSize
import com.escorp.movieworld.core.data.api.dto.actors.Actor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

internal class GetPagedPopularPeopleLiveDataUseCase @Inject constructor(
    private val getPopularPeopleUseCase: GetPopularPeopleUseCase
) : (CompositeDisposable) -> LiveData<PagedList<Actor>> {
    override fun invoke(compositeDisposable: CompositeDisposable): LiveData<PagedList<Actor>> {
        val dataSourceFactory = PopularPeoplesDataSourceFactory(getPopularPeopleUseCase, compositeDisposable)
        val config = PagedList.Config.Builder()
            .setPageSize(defaultPageSize)
            .setInitialLoadSizeHint(defaultPageSize)
            .build()
        return LivePagedListBuilder(dataSourceFactory, config).build()
    }
}