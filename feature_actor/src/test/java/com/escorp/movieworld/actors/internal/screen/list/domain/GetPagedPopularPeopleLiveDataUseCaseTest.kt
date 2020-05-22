package com.escorp.movieworld.actors.internal.screen.list.domain

import com.escorp.movieworld.core.data.api.dto.actors.Actor
import com.escorp.movieworld.test_unit_base.AndroidArchComponentExtension
import com.escorp.movieworld.test_unit_base.RxJavaExtension
import com.escorp.movieworld.test_unit_base.test
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.mockito.ArgumentMatchers.anyInt

@Extensions(
    ExtendWith(AndroidArchComponentExtension::class),
    ExtendWith(RxJavaExtension::class)
)
internal class GetPagedPopularPeopleLiveDataUseCaseTest {
    private val compositeDisposableMock = mock<CompositeDisposable>()
    private val actorMock = mock<Actor>()

    private val getPopularPeopleUseCase = mock<GetPopularPeopleUseCase>()

    private val getPagedPopularPeopleLiveDataUseCase = GetPagedPopularPeopleLiveDataUseCase(getPopularPeopleUseCase)

    @Test
    fun getPagedPopularPeopleLive() {
        val actorList = listOf(actorMock)
        whenever(getPopularPeopleUseCase.invoke(any())).thenReturn(Single.just(actorList))

        getPagedPopularPeopleLiveDataUseCase.invoke(compositeDisposableMock).test().assertValue { it.size == 1 }

        verify(getPopularPeopleUseCase).invoke(anyInt())
        verifyNoMoreInteractions(getPopularPeopleUseCase)
    }
}