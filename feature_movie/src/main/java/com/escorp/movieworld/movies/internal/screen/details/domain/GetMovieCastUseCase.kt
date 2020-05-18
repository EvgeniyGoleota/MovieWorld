package com.escorp.movieworld.movies.internal.screen.details.domain

import com.escorp.movieworld.movies.internal.data.MovieRepository
import com.escorp.movieworld.core.data.api.dto.movies.Cast
import io.reactivex.Single
import javax.inject.Inject

internal class GetMovieCastUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : (Int) -> Single<List<Cast>> {
    override fun invoke(movieId: Int): Single<List<Cast>> =
        movieRepository.getMovieCredits(movieId)
            .map { response ->
                response.cast
            }
}