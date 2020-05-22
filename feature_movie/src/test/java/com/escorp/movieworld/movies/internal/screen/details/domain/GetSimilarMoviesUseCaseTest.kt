package com.escorp.movieworld.movies.internal.screen.details.domain

import com.escorp.movieworld.core.data.api.dto.common.Response
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import com.escorp.movieworld.movies.internal.data.MovieRepository
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GetSimilarMoviesUseCaseTest {
    private val TEST_MOVIE_ID = Int.MAX_VALUE
    private val TEST_PAGE = Int.MAX_VALUE

    private val mockMovie = mock<Movie>()
    private val mockMoviesResponse = mock<Response<Movie>>()

    private val mockMovieRepository = mock<MovieRepository>()

    private val getSimilarMoviesUseCase = GetSimilarMoviesUseCase(mockMovieRepository)

    @BeforeEach
    fun setup() {
        whenever(mockMoviesResponse.results).thenReturn(listOf(mockMovie))
    }

    @Test
    fun getSimilarMovie() {
        whenever(mockMovieRepository.getSimilarMovies(any(), any())).thenReturn(Single.just(mockMoviesResponse))

        getSimilarMoviesUseCase.invoke(TEST_MOVIE_ID, TEST_PAGE).test().assertValue(mockMoviesResponse.results).assertValue{ it.size == 1 }.assertComplete()

        verify(mockMovieRepository).getSimilarMovies(TEST_MOVIE_ID, TEST_PAGE)
        verifyNoMoreInteractions(mockMovieRepository)
    }
}