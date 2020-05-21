package com.escorp.movieworld.actors.internal.screen.details.ui.credits

import com.escorp.movieworld.actors.internal.screen.details.domain.GetPersonCreditsUseCase
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import com.escorp.movieworld.test_unit_base.AndroidArchComponentExtension
import com.escorp.movieworld.test_unit_base.RxJavaExtension
import com.escorp.movieworld.test_unit_base.test
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Extensions(
    ExtendWith(AndroidArchComponentExtension::class),
    ExtendWith(RxJavaExtension::class)
)
internal class ActorCreditsViewModelTest {
    private val TEST_PERSON_ID = Int.MAX_VALUE

    private val getPersonCreditsUseCase = mock<GetPersonCreditsUseCase>()

    private val actorCreditsViewModel = ActorCreditsViewModel(getPersonCreditsUseCase)

    @Test
    fun `get credits`() {
        val fakeMovie = mock<Movie>()
        whenever(getPersonCreditsUseCase.invoke(any())).thenReturn(Single.just(listOf(fakeMovie)))

        actorCreditsViewModel.getPersonsCredits(TEST_PERSON_ID)
        actorCreditsViewModel.credits.test().assertValue { it.size == 1 }

        verify(getPersonCreditsUseCase).invoke(TEST_PERSON_ID)
        verifyNoMoreInteractions(getPersonCreditsUseCase)
    }
}