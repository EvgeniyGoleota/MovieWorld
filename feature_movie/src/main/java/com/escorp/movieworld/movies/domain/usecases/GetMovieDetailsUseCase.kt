package com.escorp.movieworld.movies.domain.usecases

import com.escorp.movieworld.movies.data.MovieRepository
import com.escorp.movieworld.network.api.dto.movies.MovieDetail
import io.reactivex.Single
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : (Int) -> Single<MovieDetail> {
    override fun invoke(movieId: Int): Single<MovieDetail> =
        movieRepository.getMovieDetails(movieId)
}