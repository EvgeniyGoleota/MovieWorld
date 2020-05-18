package com.escorp.movieworld.movies.internal.data

import com.escorp.movieworld.core.data.api.MoviesApiClient
import com.escorp.movieworld.core.data.api.dto.common.CreditsResponse
import com.escorp.movieworld.core.data.api.dto.common.Response
import com.escorp.movieworld.core.data.api.dto.movies.Cast
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import com.escorp.movieworld.core.data.api.dto.movies.MovieDetail
import io.reactivex.Single
import javax.inject.Inject

internal class MovieRepository @Inject constructor(
    private val moviesApiClient: MoviesApiClient
) {
    fun getPopularMovies(page: Int): Single<Response<Movie>> =
        moviesApiClient.getPopularMovies(page)

    fun getMovieDetails(movieId: Int): Single<MovieDetail> =
        moviesApiClient.getMovieDetail(movieId)

    fun getMovieCredits(movieId: Int): Single<CreditsResponse<Cast>> =
        moviesApiClient.getMovieCredits(movieId)

    fun getSimilarMovies(movieId: Int, page: Int): Single<Response<Movie>> =
        moviesApiClient.getSimilarMovies(movieId, page)
}