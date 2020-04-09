package com.escorp.movieworld.movies.domain.usecases

import com.escorp.movieworld.movies.data.MovieRepository
import com.escorp.movieworld.network.api.dto.movies.Movie
import io.reactivex.Single
import javax.inject.Inject

class GetSimilarMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : (Int, Int) -> Single<List<Movie>> {
    override fun invoke(movieId: Int, page: Int): Single<List<Movie>> =
        movieRepository.getSimilarMovies(movieId, page)
            .map { response ->
                response.results
            }
}