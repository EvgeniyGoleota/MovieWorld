package com.escorp.movieworld.actors.internal.screen.details.domain

import com.escorp.movieworld.actors.internal.data.ActorRepository
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import io.reactivex.Single
import javax.inject.Inject

internal class GetPersonCreditsUseCase @Inject constructor(
    private val actorRepository: ActorRepository
) : (Int) -> Single<List<Movie>> {
    override fun invoke(personId: Int): Single<List<Movie>> =
        actorRepository.getPersonCredits(personId)
            .map {
                it.cast
            }
}