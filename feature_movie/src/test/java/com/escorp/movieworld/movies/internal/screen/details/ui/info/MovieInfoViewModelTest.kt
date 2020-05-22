package com.escorp.movieworld.movies.internal.screen.details.ui.info

import com.escorp.movieworld.core.data.api.dto.movies.MovieDetail
import com.escorp.movieworld.movies.internal.screen.details.domain.GetMovieDetailsUseCase
import com.escorp.movieworld.test_unit_base.AndroidArchComponentExtension
import com.escorp.movieworld.test_unit_base.RxJavaExtension
import com.escorp.movieworld.test_unit_base.test
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Extensions(
    ExtendWith(AndroidArchComponentExtension::class),
    ExtendWith(RxJavaExtension::class)
)
internal class MovieInfoViewModelTest {
    private val TEST_MOVIE_ID = Int.MAX_VALUE

    private val movieDetailsMock = mock<MovieDetail>()

    private val getMovieDetailsUseCaseMock = mock<GetMovieDetailsUseCase>()

    private val movieInfoViewModel = MovieInfoViewModel(getMovieDetailsUseCaseMock)

    @Test
    fun getMovieDetails() {
        whenever(getMovieDetailsUseCaseMock.invoke(any())).thenReturn(Single.just(movieDetailsMock))

        movieInfoViewModel.getMovieDetails(TEST_MOVIE_ID)
        movieInfoViewModel.movie.test().assertValue(movieDetailsMock)

        verify(getMovieDetailsUseCaseMock).invoke(TEST_MOVIE_ID)
        verifyNoMoreInteractions(getMovieDetailsUseCaseMock)
    }
}