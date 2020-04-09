package com.escorp.movieworld.movies.domain.usecases

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.escorp.movieworld.core.utils.defaultPageSize
import com.escorp.movieworld.movies.data.datasourses.factories.SimilarMovieDataSourceFactory
import com.escorp.movieworld.network.api.dto.movies.Movie
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GetPagedSimilarMoviesLiveDataUseCase @Inject constructor(
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase
) : (Int, CompositeDisposable) -> LiveData<PagedList<Movie>> {
    override fun invoke(movieId: Int, compositeDisposable: CompositeDisposable): LiveData<PagedList<Movie>> {
        val dataSourceFactory = SimilarMovieDataSourceFactory(movieId, getSimilarMoviesUseCase, compositeDisposable)
        val config = PagedList.Config.Builder()
            .setPageSize(defaultPageSize)
            .setInitialLoadSizeHint(defaultPageSize)
            .build()
        return LivePagedListBuilder(dataSourceFactory, config).build()
    }
}