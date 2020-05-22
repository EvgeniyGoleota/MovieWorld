package com.escorp.movieworld.movies.internal.screen.details.ui.similar

import androidx.lifecycle.MutableLiveData
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import com.escorp.movieworld.movies.internal.screen.details.domain.GetPagedSimilarMoviesLiveDataUseCase
import com.escorp.movieworld.test_unit_base.AndroidArchComponentExtension
import com.escorp.movieworld.test_unit_base.RxJavaExtension
import com.escorp.movieworld.test_unit_base.test
import com.escorp.movieworld.test_unit_base.utils.mockPagedList
import com.nhaarman.mockitokotlin2.*
import io.reactivex.disposables.CompositeDisposable
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Extensions(
    ExtendWith(AndroidArchComponentExtension::class),
    ExtendWith(RxJavaExtension::class)
)
internal class SimilarMoviesViewModelTest {
    private val TEST_MOVIE_ID = Int.MAX_VALUE

    private val movieMock = mock<Movie>()
    private val pagedList = mockPagedList(listOf(movieMock))
    private val compositeDisposableMock = mock<CompositeDisposable>()

    private val getPagedSimilarMoviesLiveDataUseCaseMock = mock<GetPagedSimilarMoviesLiveDataUseCase>()

    private lateinit var similarMoviesViewModel: SimilarMoviesViewModel

    @BeforeEach
    fun setup() {
        whenever(getPagedSimilarMoviesLiveDataUseCaseMock.invoke(any(), any())).thenReturn( MutableLiveData(pagedList))
        similarMoviesViewModel = SimilarMoviesViewModel(getPagedSimilarMoviesLiveDataUseCaseMock, compositeDisposableMock)
    }

    @Test
    fun getSimilarMovies() {
        similarMoviesViewModel.getSimilarMovies(TEST_MOVIE_ID).test().assertValue { it.size == pagedList.size }

        verify(getPagedSimilarMoviesLiveDataUseCaseMock).invoke(TEST_MOVIE_ID, compositeDisposableMock)
        verifyNoMoreInteractions(getPagedSimilarMoviesLiveDataUseCaseMock)
    }
}