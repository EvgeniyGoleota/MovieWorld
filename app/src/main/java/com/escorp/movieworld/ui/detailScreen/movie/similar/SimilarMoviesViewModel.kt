package com.escorp.movieworld.ui.detailScreen.movie.similar

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.dataSources.factories.SimilarMovieDataSourceFactory
import com.escorp.movieworld.data.models.Movie
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SimilarMoviesViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var dataSourceFactory: SimilarMovieDataSourceFactory
    private lateinit var similarMoviesList: LiveData<PagedList<Movie>>

    fun getSimilarMovies(movieId: Int): LiveData<PagedList<Movie>> {
        dataSourceFactory = SimilarMovieDataSourceFactory(movieId, dataRepository, compositeDisposable)
        similarMoviesList = dataRepository.getPagedSimilarMoviesListLiveData(dataSourceFactory)
        return similarMoviesList
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}