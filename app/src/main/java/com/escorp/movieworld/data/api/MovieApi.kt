package com.escorp.movieworld.data.api

import com.escorp.movieworld.data.models.*
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/top_rated?language=en-US&region=US")
    fun getTopRatedMovies(@Query("page") page: Int): Observable<MovieResponse>

    @GET("movie/popular?language=en-US&region=US")
    fun getPopularMovies(@Query("page") page: Int): Observable<MovieResponse>

    @GET("person/popular?language=en-US&region=US")
    fun getPopularPeople(@Query("page") page: Int): Observable<ActorResponse>

    @GET("person/{person_id}?language=en-US&region=US")
    fun getPersonDetail(@Path("person_id") personId: Long): Observable<ActorDetail>

    @GET("person/{person_id}/images?language=en-US&region=US")
    fun getPersonPhotos(@Path("person_id") personId: Long): Flowable<APhotoResponse>

    @GET("person/{person_id}/movie_credits?language=en-US&region=US")
    fun getPersonsCombinedCredits(@Path("person_id") personId: Long): Flowable<ACreditsResponse>
}