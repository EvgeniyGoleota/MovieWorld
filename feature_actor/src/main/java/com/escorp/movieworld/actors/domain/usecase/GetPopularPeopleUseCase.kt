package com.escorp.movieworld.actors.domain.usecase

import com.escorp.movieworld.actors.data.ActorRepository
import com.escorp.movieworld.network.api.dto.actors.Actor
import io.reactivex.Single
import javax.inject.Inject

class GetPopularPeopleUseCase @Inject constructor(
    private val actorRepository: ActorRepository
) : (Int) -> Single<List<Actor>>{
    override fun invoke(page: Int): Single<List<Actor>> {
        return actorRepository.getPopularPeople(page)
            .map {
                it.results
            }
    }
}