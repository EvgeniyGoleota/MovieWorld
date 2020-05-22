package com.escorp.movieworld.movies.internal.screen.details.domain

import com.escorp.movieworld.core.data.api.dto.common.CreditsResponse
import com.escorp.movieworld.core.data.api.dto.movies.Cast
import com.escorp.movieworld.movies.internal.data.MovieRepository
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GetMovieCastUseCaseTest {
    private val TEST_MOVIE_ID = Int.MAX_VALUE

    private val mockCast = mock<Cast>()
    private val mockCreditsResponse = mock<CreditsResponse<Cast>>()

    private val mockMovieRepository = mock<MovieRepository>()

    private val getMovieCastUseCase = GetMovieCastUseCase(mockMovieRepository)

    @BeforeEach
    fun setup() {
        whenever(mockCreditsResponse.cast).thenReturn(listOf(mockCast))
    }

    @Test
    fun getMovieCast() {
        whenever(mockMovieRepository.getMovieCredits(any())).thenReturn(Single.just(mockCreditsResponse))

        getMovieCastUseCase.invoke(TEST_MOVIE_ID).test().assertValue{ it.size == 1 }.assertComplete()

        verify(mockMovieRepository).getMovieCredits(TEST_MOVIE_ID)
        verifyNoMoreInteractions(mockMovieRepository)
    }
}