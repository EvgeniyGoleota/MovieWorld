package com.escorp.movieworld.movies.internal.screen.details.domain

import com.escorp.movieworld.core.data.api.dto.movies.MovieDetail
import com.escorp.movieworld.movies.internal.data.MovieRepository
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.jupiter.api.Test

internal class GetMovieDetailsUseCaseTest {
    private val TEST_MOVIE_ID = Int.MAX_VALUE

    private val mockDetailResponse = mock<MovieDetail>()

    private val mockMovieRepository = mock<MovieRepository>()

    private val getMovieDetailsUseCase = GetMovieDetailsUseCase(mockMovieRepository)

    @Test
    fun getMovieDetail() {
        whenever(mockMovieRepository.getMovieDetails(any())).thenReturn(Single.just(mockDetailResponse))

        getMovieDetailsUseCase.invoke(TEST_MOVIE_ID).test().assertValue(mockDetailResponse).assertComplete()

        verify(mockMovieRepository).getMovieDetails(TEST_MOVIE_ID)
        verifyNoMoreInteractions(mockMovieRepository)
    }
}