package com.escorp.movieworld.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.escorp.movieworld.data.DataRepository
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    fun getListOfTopRatedMovies() = dataRepository.getTopRatedMovies()

}
