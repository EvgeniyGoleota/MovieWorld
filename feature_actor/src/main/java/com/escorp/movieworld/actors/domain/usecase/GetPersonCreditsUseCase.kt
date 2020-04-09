package com.escorp.movieworld.actors.domain.usecase

import com.escorp.movieworld.actors.data.ActorRepository
import com.escorp.movieworld.network.api.dto.movies.Movie
import io.reactivex.Single
import javax.inject.Inject

class GetPersonCreditsUseCase @Inject constructor(
    private val actorRepository: ActorRepository
) : (Int) -> Single<List<Movie>> {
    override fun invoke(personId: Int): Single<List<Movie>> =
        actorRepository.getPersonCredits(personId)
            .map {
                it.cast
            }
}