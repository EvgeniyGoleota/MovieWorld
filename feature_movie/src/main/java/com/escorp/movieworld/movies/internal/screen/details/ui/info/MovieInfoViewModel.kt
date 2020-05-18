package com.escorp.movieworld.movies.internal.screen.details.ui.info

import androidx.databinding.ObservableField
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.movies.internal.screen.details.domain.GetMovieDetailsUseCase
import com.escorp.movieworld.core.data.api.dto.movies.MovieDetail
import javax.inject.Inject

internal class MovieInfoViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModelBase() {

    val movie: ObservableField<MovieDetail> = ObservableField()

    fun getMovieDetails(movieId: Int) {
        getMovieDetailsUseCase(movieId)
            .subscribeIoObserveMain()
            .toObservable()
            .toRecoverable()
            .autoDisposableSubscribe(movie::set)

    }
}