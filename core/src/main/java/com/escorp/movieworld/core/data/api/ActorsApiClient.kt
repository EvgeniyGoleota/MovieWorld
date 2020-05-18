package com.escorp.movieworld.core.data.api

import com.escorp.movieworld.core.data.api.dto.actors.APhotoResponse
import com.escorp.movieworld.core.data.api.dto.actors.Actor
import com.escorp.movieworld.core.data.api.dto.actors.ActorDetail
import com.escorp.movieworld.core.data.api.dto.common.CreditsResponse
import com.escorp.movieworld.core.data.api.dto.common.Response
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ActorsApiClient {

    @GET("person/popular?language=en-US&region=US")
    fun getPopularPeople(@Query("page") page: Int): Single<Response<Actor>>

    @GET("person/{person_id}?language=en-US&region=US")
    fun getPersonDetail(@Path("person_id") personId: Int): Single<ActorDetail>

    @GET("person/{person_id}/images?language=en-US&region=US")
    fun getPersonPhotos(@Path("person_id") personId: Int): Single<APhotoResponse>

    @GET("person/{person_id}/movie_credits?language=en-US&region=US")
    fun getPersonsCombinedCredits(@Path("person_id") personId: Int): Single<CreditsResponse<Movie>>
}