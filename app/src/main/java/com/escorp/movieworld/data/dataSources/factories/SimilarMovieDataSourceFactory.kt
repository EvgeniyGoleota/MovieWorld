package com.escorp.movieworld.data.dataSources.factories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.dataSources.SimilarMoviesDataSource
import com.escorp.movieworld.data.models.Movie
import io.reactivex.disposables.CompositeDisposable

class SimilarMovieDataSourceFactory(
    private val movieId: Int,
    private val dataRepository: DataRepository,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Movie>() {

    val sourceLiveData = MutableLiveData<SimilarMoviesDataSource>()
    var latestSource: SimilarMoviesDataSource? = null

    override fun create(): DataSource<Int, Movie> {
        latestSource =
            SimilarMoviesDataSource(
                movieId,
                dataRepository,
                compositeDisposable
            )
        sourceLiveData.postValue(latestSource)
        return latestSource!!
    }
}