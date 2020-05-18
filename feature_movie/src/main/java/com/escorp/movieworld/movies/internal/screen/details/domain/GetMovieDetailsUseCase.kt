package com.escorp.movieworld.movies.internal.screen.details.domain

import com.escorp.movieworld.movies.internal.data.MovieRepository
import com.escorp.movieworld.core.data.api.dto.movies.MovieDetail
import io.reactivex.Single
import javax.inject.Inject

internal class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : (Int) -> Single<MovieDetail> {
    override fun invoke(movieId: Int): Single<MovieDetail> =
        movieRepository.getMovieDetails(movieId)
}