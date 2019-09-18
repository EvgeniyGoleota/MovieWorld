package com.escorp.movieworld.ui.detailScreen.movieDetail.movieCast

import androidx.lifecycle.ViewModel
import com.escorp.movieworld.data.DataRepository
import javax.inject.Inject

class MovieCastViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    fun getMovieCast(movieId: Int) = dataRepository.getMovieCredits(movieId)
}