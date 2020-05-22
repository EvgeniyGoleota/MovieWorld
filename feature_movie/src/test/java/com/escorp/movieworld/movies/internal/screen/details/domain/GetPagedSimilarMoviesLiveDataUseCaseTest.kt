package com.escorp.movieworld.movies.internal.screen.details.domain

import com.escorp.movieworld.core.data.api.dto.movies.Movie
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
internal class GetPagedSimilarMoviesLiveDataUseCaseTest {
    private val TEST_MOVIE_ID = Int.MAX_VALUE

    private val compositeDisposableMock = mock<CompositeDisposable>()
    private val mockMovie = mock<Movie>()

    private val getSimilarMoviesUseCaseMock = mock<GetSimilarMoviesUseCase>()

    private val getPagedSimilarMoviesLiveDataUseCase = GetPagedSimilarMoviesLiveDataUseCase(getSimilarMoviesUseCaseMock)

    @Test
    fun getPagedSimilarMoviesLiveData() {
        val movieList = listOf(mockMovie)
        whenever(getSimilarMoviesUseCaseMock.invoke(any(), any())).thenReturn(Single.just(movieList))

        getPagedSimilarMoviesLiveDataUseCase.invoke(TEST_MOVIE_ID, compositeDisposableMock).test().assertValue { it.size == 1 }

        verify(getSimilarMoviesUseCaseMock).invoke(eq(TEST_MOVIE_ID), anyInt())
        verifyNoMoreInteractions(getSimilarMoviesUseCaseMock)
    }
}