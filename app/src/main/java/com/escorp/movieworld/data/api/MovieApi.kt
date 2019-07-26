package com.escorp.movieworld.data.api

import com.escorp.movieworld.data.api.models.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieApi {

    @GET("movie/top_rated?language=en-US&region=US&page=1")
    fun getTopRatedMovies(): Observable<MovieResponse>
}