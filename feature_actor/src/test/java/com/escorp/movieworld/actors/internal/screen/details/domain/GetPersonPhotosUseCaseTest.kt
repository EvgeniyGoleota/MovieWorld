package com.escorp.movieworld.actors.internal.screen.details.domain

import com.escorp.movieworld.actors.internal.data.ActorRepository
import com.escorp.movieworld.core.data.api.dto.actors.APhotoResponse
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.jupiter.api.Test

internal class GetPersonPhotosUseCaseTest {
    private val TEST_ACTOR_ID = Int.MAX_VALUE
    private val TEST_PHOTO_RESPONSE = APhotoResponse()

    private val actorRepository = mock<ActorRepository>()

    private val getPersonPhotosUseCase = GetPersonPhotosUseCase(actorRepository)

    @Test
    fun `get persons photos`() {
        whenever(actorRepository.getPersonPhotos(any())).thenReturn(Single.just(TEST_PHOTO_RESPONSE))

        getPersonPhotosUseCase.invoke(TEST_ACTOR_ID).test().assertValue(TEST_PHOTO_RESPONSE.profiles).assertComplete()

        verify(actorRepository).getPersonPhotos(TEST_ACTOR_ID)
        verifyNoMoreInteractions(actorRepository)
    }
}