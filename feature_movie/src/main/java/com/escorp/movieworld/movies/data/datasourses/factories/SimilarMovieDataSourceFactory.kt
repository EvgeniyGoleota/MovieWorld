package com.escorp.movieworld.movies.data.datasourses.factories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.escorp.movieworld.movies.data.datasourses.SimilarMoviesDataSource
import com.escorp.movieworld.movies.domain.usecases.GetSimilarMoviesUseCase
import com.escorp.movieworld.network.api.dto.movies.Movie
import io.reactivex.disposables.CompositeDisposable

class SimilarMovieDataSourceFactory(
    private val movieId: Int,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Movie>() {

    private val sourceLiveData = MutableLiveData<SimilarMoviesDataSource>()
    var latestSource: SimilarMoviesDataSource? = null

    override fun create(): DataSource<Int, Movie> {
        latestSource =
            SimilarMoviesDataSource(
                movieId,
                getSimilarMoviesUseCase,
                compositeDisposable
            )
        sourceLiveData.postValue(latestSource)
        return latestSource!!
    }
}