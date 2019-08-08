package com.escorp.movieworld.data.api

import com.escorp.movieworld.data.api.models.MovieResponse
import com.escorp.movieworld.data.api.models.ActorResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/top_rated?language=en-US&region=US")
    fun getTopRatedMovies(@Query("page") page: Int): Flowable<MovieResponse>

    @GET("person/popular?language=en-US&region=US")
    fun getPopularPeople(@Query("page") page: Int): Flowable<ActorResponse>
}