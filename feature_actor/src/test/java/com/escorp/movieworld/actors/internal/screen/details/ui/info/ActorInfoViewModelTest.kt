package com.escorp.movieworld.actors.internal.screen.details.ui.info

import com.escorp.movieworld.actors.internal.screen.details.domain.GetPersonDetailsUseCase
import com.escorp.movieworld.core.data.api.dto.actors.ActorDetail
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
internal class ActorInfoViewModelTest {
    private val TEST_PERSON_ID = Int.MAX_VALUE
    private val TEST_DETAILS_RESPONSE = mock<ActorDetail>()

    private val getPersonDetailsUseCaseMock = mock<GetPersonDetailsUseCase>()

    private val actorInfoViewModel = ActorInfoViewModel(getPersonDetailsUseCaseMock)

    @Test
    fun retrievePersonDetails() {
        whenever(getPersonDetailsUseCaseMock.invoke(any())).thenReturn(Single.just(TEST_DETAILS_RESPONSE))

        actorInfoViewModel.retrievePersonDetails(TEST_PERSON_ID)
        actorInfoViewModel.person.test().assertValue(TEST_DETAILS_RESPONSE)

        verify(getPersonDetailsUseCaseMock).invoke(TEST_PERSON_ID)
        verifyNoMoreInteractions(getPersonDetailsUseCaseMock)
    }
}