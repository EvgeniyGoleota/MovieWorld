package com.escorp.movieworld.actors.internal.screen.details.domain

import com.escorp.movieworld.actors.internal.data.ActorRepository
import com.escorp.movieworld.core.data.api.dto.actors.ActorDetail
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.jupiter.api.Test

internal class GetPersonDetailsUseCaseTest {
    private val TEST_ACTOR_ID = Int.MAX_VALUE

    private val mockResponse = mock<ActorDetail>()

    private val actorRepository = mock<ActorRepository>()

    private val getPersonDetailsUseCase = GetPersonDetailsUseCase(actorRepository)

    @Test
    fun `get personal details`() {
        whenever(actorRepository.getPersonDetails(any())).thenReturn(Single.just(mockResponse))

        getPersonDetailsUseCase.invoke(TEST_ACTOR_ID).test().assertValue(mockResponse).assertComplete()

        verify(actorRepository).getPersonDetails(TEST_ACTOR_ID)
        verifyNoMoreInteractions(actorRepository)
    }
}