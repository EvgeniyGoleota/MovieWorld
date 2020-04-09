package com.escorp.movieworld.movies.domain.usecases

import com.escorp.movieworld.movies.data.MovieRepository
import com.escorp.movieworld.network.api.dto.actors.Actor
import com.escorp.movieworld.network.api.dto.movies.Cast
import io.reactivex.Single
import javax.inject.Inject

class GetMovieCastUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : (Int) -> Single<List<Cast>> {
    override fun invoke(movieId: Int): Single<List<Cast>> =
        movieRepository.getMovieCredits(movieId)
            .map { response ->
                response.cast
            }
}