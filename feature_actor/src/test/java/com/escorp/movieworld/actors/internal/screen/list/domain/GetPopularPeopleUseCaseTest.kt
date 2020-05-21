package com.escorp.movieworld.actors.internal.screen.list.domain

import com.escorp.movieworld.actors.internal.data.ActorRepository
import com.escorp.movieworld.core.data.api.dto.actors.Actor
import com.escorp.movieworld.core.data.api.dto.common.Response
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.jupiter.api.Test

internal class GetPopularPeopleUseCaseTest {
    private val TEST_PAGE = Long.MAX_VALUE

    private val actorMock = mock<Actor>()
    private val TEST_ACTOR_RESPONSE = Response(TEST_PAGE, listOf(actorMock), Long.MAX_VALUE, Long.MAX_VALUE)

    private val actorRepository = mock<ActorRepository>()

    private val getPopularPeopleUseCase = GetPopularPeopleUseCase(actorRepository)


    @Test
    fun `get popular people`() {
        whenever(actorRepository.getPopularPeople(any())).thenReturn(Single.just(TEST_ACTOR_RESPONSE))

        getPopularPeopleUseCase.invoke(TEST_PAGE.toInt()).test().assertValue { it.size == 1 }

        verify(actorRepository).getPopularPeople(TEST_PAGE.toInt())
        verifyNoMoreInteractions(actorRepository)
    }
}