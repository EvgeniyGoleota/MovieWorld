package com.escorp.movieworld.actors.domain.usecase

import com.escorp.movieworld.actors.data.ActorRepository
import com.escorp.movieworld.network.api.ActorsApiClient
import com.escorp.movieworld.network.api.dto.common.Image
import io.reactivex.Single
import javax.inject.Inject

class GetPersonPhotosUseCase @Inject constructor(
    private val actorRepository: ActorRepository
) : (Int) -> Single<List<Image>> {
    override fun invoke(personId: Int): Single<List<Image>> =
        actorRepository.getPersonPhotos(personId)
            .map {
                it.profiles
            }
}