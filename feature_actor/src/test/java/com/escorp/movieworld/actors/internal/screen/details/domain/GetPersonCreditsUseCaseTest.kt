package com.escorp.movieworld.actors.internal.screen.details.domain

import com.escorp.movieworld.actors.internal.data.ActorRepository
import com.escorp.movieworld.core.data.api.dto.common.CreditsResponse
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.jupiter.api.Test

internal class GetPersonCreditsUseCaseTest {
    private val TEST_ACTOR_ID = Int.MAX_VALUE
    private val TEST_CREDITS_RESPONSE = CreditsResponse<Movie>()


    private val actorRepository = mock<ActorRepository>()

    private val getPersonCreditsUseCase = GetPersonCreditsUseCase(actorRepository)


    @Test
    fun `getting credits`() {
        whenever(actorRepository.getPersonCredits(any()))
            .thenReturn(Single.just(TEST_CREDITS_RESPONSE))

        getPersonCreditsUseCase.invoke(TEST_ACTOR_ID).test().assertValue(TEST_CREDITS_RESPONSE.cast).assertComplete()

        verify(actorRepository).getPersonCredits(TEST_ACTOR_ID)
        verifyNoMoreInteractions(actorRepository)
    }
}