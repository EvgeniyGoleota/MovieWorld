package com.escorp.movieworld.actors.internal.screen.details.domain

import com.escorp.movieworld.actors.internal.data.ActorRepository
import com.escorp.movieworld.core.data.api.dto.actors.ActorDetail
import io.reactivex.Single
import javax.inject.Inject

internal class GetPersonDetailsUseCase @Inject constructor(
    private val actorRepository: ActorRepository
): (Int) -> Single<ActorDetail> {
    override fun invoke(personId: Int): Single<ActorDetail> {
        return actorRepository.getPersonDetails(personId)
    }
}