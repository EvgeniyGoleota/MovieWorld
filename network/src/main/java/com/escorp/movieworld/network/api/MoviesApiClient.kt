package com.escorp.movieworld.network.api

import com.escorp.movieworld.network.api.dto.actors.Actor
import com.escorp.movieworld.network.api.dto.common.CreditsResponse
import com.escorp.movieworld.network.api.dto.common.Response
import com.escorp.movieworld.network.api.dto.movies.Cast
import com.escorp.movieworld.network.api.dto.movies.Movie
import com.escorp.movieworld.network.api.dto.movies.MovieDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiClient {

    @GET("movie/top_rated?language=en-US&region=US")
    fun getTopRatedMovies(@Query("page") page: Int): Single<Response<Movie>>

    @GET("movie/popular?language=en-US&region=US")
    fun getPopularMovies(@Query("page") page: Int): Single<Response<Movie>>

    @GET("movie/{movie_id}?language=en-US&region=US")
    fun getMovieDetail(@Path("movie_id") movieId: Int): Single<MovieDetail>

    @GET("movie/{movie_id}/credits?language=en-US&region=US")
    fun getMovieCredits(@Path("movie_id") movieId: Int): Single<CreditsResponse<Cast>>

    @GET("movie/{movie_id}/similar?language=en-US&region=US")
    fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): Single<Response<Movie>>
}