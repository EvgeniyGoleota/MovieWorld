package com.escorp.movieworld.data.api

import com.escorp.movieworld.data.api.models.MovieResponse
import com.escorp.movieworld.data.api.models.PeopleResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface MovieApi {

    @GET("movie/top_rated?language=en-US&region=US&page=1")
    fun getTopRatedMovies(): Flowable<MovieResponse>

    @GET("person/popular?language=en-US&region=US&page=1")
    fun getPopularPeople(): Flowable<PeopleResponse>
}