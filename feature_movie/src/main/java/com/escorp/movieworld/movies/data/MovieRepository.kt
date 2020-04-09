package com.escorp.movieworld.movies.data

import com.escorp.movieworld.network.api.MoviesApiClient
import com.escorp.movieworld.network.api.dto.actors.Actor
import com.escorp.movieworld.network.api.dto.common.CreditsResponse
import com.escorp.movieworld.network.api.dto.common.Response
import com.escorp.movieworld.network.api.dto.movies.Cast
import com.escorp.movieworld.network.api.dto.movies.Movie
import com.escorp.movieworld.network.api.dto.movies.MovieDetail
import io.reactivex.Single
import javax.inject.Inject

interface MovieRepository {
    fun getPopularMovies(page: Int): Single<Response<Movie>>
    fun getMovieDetails(movieId: Int): Single<MovieDetail>
    fun getMovieCredits(movieId: Int): Single<CreditsResponse<Cast>>
    fun getSimilarMovies(movieId: Int, page: Int): Single<Response<Movie>>
}

internal class MovieRepositoryImpl @Inject constructor(
    private val moviesApiClient: MoviesApiClient
): MovieRepository {
    override fun getPopularMovies(page: Int): Single<Response<Movie>> =
        moviesApiClient.getPopularMovies(page)

    override fun getMovieDetails(movieId: Int): Single<MovieDetail> =
        moviesApiClient.getMovieDetail(movieId)

    override fun getMovieCredits(movieId: Int): Single<CreditsResponse<Cast>> =
        moviesApiClient.getMovieCredits(movieId)

    override fun getSimilarMovies(movieId: Int, page: Int): Single<Response<Movie>> =
        moviesApiClient.getSimilarMovies(movieId, page)
}