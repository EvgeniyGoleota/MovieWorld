package com.escorp.movieworld.actors.internal.screen.details.domain

import com.escorp.movieworld.actors.internal.data.ActorRepository
import com.escorp.movieworld.core.data.api.dto.common.Image
import io.reactivex.Single
import javax.inject.Inject

internal class GetPersonPhotosUseCase @Inject constructor(
    private val actorRepository: ActorRepository
) : (Int) -> Single<List<Image>> {
    override fun invoke(personId: Int): Single<List<Image>> =
        actorRepository.getPersonPhotos(personId)
            .map {
                it.profiles
            }
}