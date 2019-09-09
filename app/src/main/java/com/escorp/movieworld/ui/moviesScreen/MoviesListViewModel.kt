package com.escorp.movieworld.ui.moviesScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.api.models.Movie
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    val pagedMoviesListLiveData: LiveData<PagedList<Movie>> by lazy {
        dataRepository.getPagedMovieListLiveData()
    }

    fun retrieveTopRatedMovies(page: Int) = dataRepository.getTopRatedMovies(page)
}
