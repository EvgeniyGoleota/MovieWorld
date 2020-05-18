package com.escorp.movieworld.movies.internal.screen.details.ui.cast

import androidx.lifecycle.LiveData
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.movies.internal.screen.details.domain.GetMovieCastUseCase
import com.escorp.movieworld.core.data.api.dto.movies.Cast
import javax.inject.Inject

internal class MovieCastViewModel @Inject constructor(private val getMovieCastUseCase: GetMovieCastUseCase) : ViewModelBase() {

    fun getMovieCast(movieId: Int): LiveData<List<Cast>> =
        getMovieCastUseCase(movieId)
            .subscribeIoObserveMain()
            .toObservable()
            .toRecoverable()
            .toAutoDisposableLiveData()
}