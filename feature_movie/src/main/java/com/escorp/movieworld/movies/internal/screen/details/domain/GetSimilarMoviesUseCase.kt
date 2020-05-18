package com.escorp.movieworld.movies.internal.screen.details.domain

import com.escorp.movieworld.movies.internal.data.MovieRepository
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import io.reactivex.Single
import javax.inject.Inject

internal class GetSimilarMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : (Int, Int) -> Single<List<Movie>> {
    override fun invoke(movieId: Int, page: Int): Single<List<Movie>> =
        movieRepository.getSimilarMovies(movieId, page)
            .map { response ->
                response.results
            }
}