package com.escorp.movieworld.movies.internal.screen.list.domain

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
internal class GetPagedPopularMoviesLiveDataUseCaseTest {

    private val compositeDisposableMock = mock<CompositeDisposable>()
    private val mockMovie = mock<Movie>()

    private val getPopularMoviesUseCaseMock = mock<GetPopularMoviesUseCase>()

    private val getPagedPopularMoviesLiveDataUseCase = GetPagedPopularMoviesLiveDataUseCase(getPopularMoviesUseCaseMock)

    @Test
    fun getPagedPopularMoviesLiveData() {
        val movieList = listOf(mockMovie)
        whenever(getPopularMoviesUseCaseMock.invoke(any())).thenReturn(Single.just(movieList))

        getPagedPopularMoviesLiveDataUseCase.invoke(compositeDisposableMock).test().assertValue { it.size == movieList.size }

        verify(getPopularMoviesUseCaseMock).invoke(anyInt())
        verifyNoMoreInteractions(getPopularMoviesUseCaseMock)
    }
}