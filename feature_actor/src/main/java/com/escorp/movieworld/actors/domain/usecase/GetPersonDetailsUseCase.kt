package com.escorp.movieworld.actors.domain.usecase

import com.escorp.movieworld.actors.data.ActorRepository
import com.escorp.movieworld.network.api.dto.actors.ActorDetail
import io.reactivex.Single
import javax.inject.Inject

class GetPersonDetailsUseCase @Inject constructor(
    private val actorRepository: ActorRepository
): (Int) -> Single<ActorDetail> {
    override fun invoke(personId: Int): Single<ActorDetail> {
        return actorRepository.getPersonDetails(personId)
    }
}