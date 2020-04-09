package com.escorp.movieworld.movies.details.ui.info

import androidx.databinding.ObservableField
import com.escorp.movieworld.core.ui.base.ViewModelBase
import com.escorp.movieworld.movies.domain.usecases.GetMovieDetailsUseCase
import com.escorp.movieworld.network.api.dto.movies.MovieDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieInfoViewModel @Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase) : ViewModelBase() {

    val movie: ObservableField<MovieDetail> = ObservableField()

    fun getMovieDetails(movieId: Int) {
        getMovieDetailsUseCase(movieId)
            .subscribeIoObserveMain()
            .toObservable()
            .toRecoverable()
            .autoDisposableSubscribe(movie::set)

    }
}