package com.escorp.movieworld.movies.internal.screen.list.domain

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.escorp.movieworld.core.utils.defaultPageSize
import com.escorp.movieworld.movies.internal.data.datasourses.factories.PopularMoviesDataSourceFactory
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

internal class GetPagedPopularMoviesLiveDataUseCase @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : (CompositeDisposable) -> LiveData<PagedList<Movie>> {
    override fun invoke(compositeDisposable: CompositeDisposable): LiveData<PagedList<Movie>> {
        val dataSourceFactory = PopularMoviesDataSourceFactory(getPopularMoviesUseCase, compositeDisposable)
        val config = PagedList.Config.Builder()
            .setPageSize(defaultPageSize)
            .setInitialLoadSizeHint(defaultPageSize)
            .build()
        return LivePagedListBuilder(dataSourceFactory, config).build()
    }
}