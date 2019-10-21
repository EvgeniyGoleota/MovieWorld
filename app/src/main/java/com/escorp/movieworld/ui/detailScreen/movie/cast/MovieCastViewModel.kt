package com.escorp.movieworld.ui.detailScreen.movie.cast

import androidx.lifecycle.ViewModel
import com.escorp.movieworld.data.DataRepository
import javax.inject.Inject

class MovieCastViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    fun getMovieCast(movieId: Int) = dataRepository.getMovieCredits(movieId)
}