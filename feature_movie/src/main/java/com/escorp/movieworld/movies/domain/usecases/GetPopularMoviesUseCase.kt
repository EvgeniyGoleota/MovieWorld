package com.escorp.movieworld.movies.domain.usecases

import com.escorp.movieworld.movies.data.MovieRepository
import com.escorp.movieworld.network.api.dto.movies.Movie
import io.reactivex.Single
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : (Int) -> Single<List<Movie>> {
    override fun invoke(page: Int): Single<List<Movie>> =
        movieRepository.getPopularMovies(page)
            .map { response ->
                response.results
            }
}