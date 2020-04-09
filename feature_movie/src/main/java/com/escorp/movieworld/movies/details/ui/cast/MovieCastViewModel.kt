package com.escorp.movieworld.movies.details.ui.cast

import androidx.lifecycle.LiveData
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.movies.domain.usecases.GetMovieCastUseCase
import com.escorp.movieworld.network.api.dto.actors.Actor
import com.escorp.movieworld.network.api.dto.movies.Cast
import javax.inject.Inject

class MovieCastViewModel @Inject constructor(private val getMovieCastUseCase: GetMovieCastUseCase) : ViewModelBase() {

    fun getMovieCast(movieId: Int): LiveData<List<Cast>> =
        getMovieCastUseCase(movieId)
            .subscribeIoObserveMain()
            .toObservable()
            .toRecoverable()
            .toAutoDisposableLiveData()
}