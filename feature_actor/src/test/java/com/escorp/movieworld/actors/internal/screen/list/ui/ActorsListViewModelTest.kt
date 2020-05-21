package com.escorp.movieworld.actors.internal.screen.list.ui

import androidx.lifecycle.MutableLiveData
import com.escorp.movieworld.actors.internal.screen.list.domain.GetPagedPopularPeopleLiveDataUseCase
import com.escorp.movieworld.core.data.api.dto.actors.Actor
import com.escorp.movieworld.test_unit_base.AndroidArchComponentExtension
import com.escorp.movieworld.test_unit_base.RxJavaExtension
import com.escorp.movieworld.test_unit_base.test
import com.escorp.movieworld.test_unit_base.utils.mockPagedList
import com.nhaarman.mockitokotlin2.*
import io.reactivex.disposables.CompositeDisposable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Extensions(
    ExtendWith(AndroidArchComponentExtension::class),
    ExtendWith(RxJavaExtension::class)
)
internal class ActorsListViewModelTest {

    private val actorMock = mock<Actor>()
    private val pagedList = mockPagedList(listOf(actorMock))
    private val compositeDisposableMock = CompositeDisposable()

    private val getPagedPopularPeopleLiveDataUseCase = mock<GetPagedPopularPeopleLiveDataUseCase>()

    private lateinit var actorsListViewModel: ActorsListViewModel

    @BeforeEach
    fun setup() {
        whenever(getPagedPopularPeopleLiveDataUseCase.invoke(any())).thenReturn( MutableLiveData(pagedList))
        actorsListViewModel = ActorsListViewModel(getPagedPopularPeopleLiveDataUseCase, compositeDisposableMock)
    }

    @Test
    fun getPopularPeoplePagedListLiveData() {
        actorsListViewModel.popularPeoplePagedListLiveData.test().assertValue { it.size == pagedList.size }

        verify(getPagedPopularPeopleLiveDataUseCase).invoke(compositeDisposableMock)
        verifyNoMoreInteractions(getPagedPopularPeopleLiveDataUseCase)
    }
}