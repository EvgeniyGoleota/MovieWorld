package com.escorp.movieworld.actors.internal.screen.list.domain

import com.escorp.movieworld.actors.internal.data.ActorRepository
import com.escorp.movieworld.core.data.api.dto.actors.Actor
import io.reactivex.Single
import javax.inject.Inject

internal class GetPopularPeopleUseCase @Inject constructor(
    private val actorRepository: ActorRepository
) : (Int) -> Single<List<Actor>>{
    override fun invoke(page: Int): Single<List<Actor>> {
        return actorRepository.getPopularPeople(page)
            .map {
                it.results
            }
    }
}