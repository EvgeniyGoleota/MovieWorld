package com.escorp.movieworld.movies.internal.screen.list.domain

import com.escorp.movieworld.core.data.api.dto.common.Response
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import com.escorp.movieworld.movies.internal.data.MovieRepository
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GetPopularMoviesUseCaseTest {
    private val TEST_PAGE = Int.MAX_VALUE

    private val movieMock = mock<Movie>()
    private val movieResponseMock = mock<Response<Movie>>()

    private val movieRepositoryMock = mock<MovieRepository>()

    private val getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepositoryMock)

    @BeforeEach
    fun setup() {
        whenever(movieResponseMock.results).thenReturn(listOf(movieMock))
    }

    @Test
    fun getPopularMovies() {
        whenever(movieRepositoryMock.getPopularMovies(any())).thenReturn(Single.just(movieResponseMock))

        getPopularMoviesUseCase.invoke(TEST_PAGE).test().assertValue(movieResponseMock.results).assertValue { it.size == 1 }

        verify(movieRepositoryMock).getPopularMovies(TEST_PAGE)
        verifyNoMoreInteractions(movieRepositoryMock)
    }
}