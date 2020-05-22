package com.escorp.movieworld.movies.internal.data

import com.escorp.movieworld.core.data.api.MoviesApiClient
import com.escorp.movieworld.core.data.api.dto.common.CreditsResponse
import com.escorp.movieworld.core.data.api.dto.common.Response
import com.escorp.movieworld.core.data.api.dto.movies.Cast
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import com.escorp.movieworld.core.data.api.dto.movies.MovieDetail
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.jupiter.api.Test

internal class MovieRepositoryTest {
    private val TEST_MOVIE_ID = Int.MAX_VALUE
    private val TEST_PAGE = Int.MAX_VALUE

    private val TEST_MOVIE_RESPONCE = mock<Response<Movie>>()
    private val TEST_DETAILS_RESPONCE = mock<MovieDetail>()
    private val TEST_CREDITS_RESPONCE = mock<CreditsResponse<Cast>>()

    private val mockMoviesApiClient = mock<MoviesApiClient>()

    private val movieRepository = MovieRepository(mockMoviesApiClient)

    @Test
    fun getPopularMovies() {
        whenever(mockMoviesApiClient.getPopularMovies(any())).thenReturn(Single.just(TEST_MOVIE_RESPONCE))

        movieRepository.getPopularMovies(TEST_PAGE).test().assertComplete()

        verify(mockMoviesApiClient).getPopularMovies(TEST_MOVIE_ID)
        verifyNoMoreInteractions(mockMoviesApiClient)
    }

    @Test
    fun getMovieDetails() {
        whenever(mockMoviesApiClient.getMovieDetail(any())).thenReturn(Single.just(TEST_DETAILS_RESPONCE))

        movieRepository.getMovieDetails(TEST_MOVIE_ID).test().assertComplete()

        verify(mockMoviesApiClient).getMovieDetail(TEST_MOVIE_ID)
        verifyNoMoreInteractions(mockMoviesApiClient)
    }

    @Test
    fun getMovieCredits() {
        whenever(mockMoviesApiClient.getMovieCredits(any())).thenReturn(Single.just(TEST_CREDITS_RESPONCE))

        movieRepository.getMovieCredits(TEST_PAGE).test().assertComplete()

        verify(mockMoviesApiClient).getMovieCredits(TEST_MOVIE_ID)
        verifyNoMoreInteractions(mockMoviesApiClient)
    }

    @Test
    fun getSimilarMovies() {
        whenever(mockMoviesApiClient.getSimilarMovies(any(), any())).thenReturn(Single.just(TEST_MOVIE_RESPONCE))

        movieRepository.getSimilarMovies(TEST_MOVIE_ID, TEST_PAGE).test().assertComplete()

        verify(mockMoviesApiClient).getSimilarMovies(TEST_MOVIE_ID, TEST_PAGE)
        verifyNoMoreInteractions(mockMoviesApiClient)
    }
}