package com.escorp.movieworld.movies.internal.data.datasourses.factories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.escorp.movieworld.movies.internal.data.datasourses.PopularMoviesDataSource
import com.escorp.movieworld.movies.internal.screen.list.domain.GetPopularMoviesUseCase
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import io.reactivex.disposables.CompositeDisposable

internal class PopularMoviesDataSourceFactory(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Movie>() {

    val sourceLiveData = MutableLiveData<PopularMoviesDataSource>()
    var latestSource: PopularMoviesDataSource? = null

    override fun create(): DataSource<Int, Movie> {
        latestSource =
            PopularMoviesDataSource(
                getPopularMoviesUseCase,
                compositeDisposable
            )
        sourceLiveData.postValue(latestSource)
        return latestSource!!
    }
}