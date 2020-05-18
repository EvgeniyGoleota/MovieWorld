package com.escorp.movieworld.movies.internal.screen.list.domain

import com.escorp.movieworld.movies.internal.data.MovieRepository
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import io.reactivex.Single
import javax.inject.Inject

internal class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : (Int) -> Single<List<Movie>> {
    override fun invoke(page: Int): Single<List<Movie>> =
        movieRepository.getPopularMovies(page)
            .map { response ->
                response.results
            }
}