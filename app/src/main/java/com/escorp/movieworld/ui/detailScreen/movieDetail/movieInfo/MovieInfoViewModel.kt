package com.escorp.movieworld.ui.detailScreen.movieDetail.movieInfo

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.models.MovieDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieInfoViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val movie: ObservableField<MovieDetail> = ObservableField()

    fun getMovieDetails(movieId: Int) {
        dataRepository.getMovieDetail(movieId)
            .doOnError { error ->
                error.printStackTrace()
            }
            .doOnSubscribe {
                compositeDisposable.add(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(movie::set)
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}