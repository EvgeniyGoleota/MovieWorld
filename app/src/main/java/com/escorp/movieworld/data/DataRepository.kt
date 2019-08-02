package com.escorp.movieworld.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.escorp.movieworld.data.api.MovieApi
import com.escorp.movieworld.data.api.models.Movie
import com.escorp.movieworld.data.api.models.MovieResponse
import com.escorp.movieworld.data.api.models.People
import com.escorp.movieworld.data.api.models.PeopleResponse
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class DataRepository(private val movieApi: MovieApi) {

    fun getTopRatedMovies(): LiveData<List<Movie>> = LiveDataReactiveStreams.fromPublisher(movieApi.getTopRatedMovies()
        .subscribeOn(Schedulers.io())
        .doOnError { error ->
            error.printStackTrace()
            Log.e("MW:::", "Network error while receiving top rated movies: ${error.message}")
        }
        .onErrorReturnItem(MovieResponse())
        .filter { response ->
            return@filter response.results.isNotEmpty()
        }
        .map { movieResponse: MovieResponse ->
            return@map movieResponse.results
        })

    fun getPopularPeople(): LiveData<List<People>> = LiveDataReactiveStreams.fromPublisher(movieApi.getPopularPeople()
        .subscribeOn(Schedulers.io())
        .doOnError { error ->
            error.printStackTrace()
            Log.e("MW:::", "Network error while receiving popular peoples: ${error.message}")
        }
        .onErrorReturnItem(PeopleResponse())
        .filter { response ->
            return@filter response.results.isNotEmpty()
        }
        .map { peopleResponse: PeopleResponse ->
            return@map peopleResponse.results
        })
}