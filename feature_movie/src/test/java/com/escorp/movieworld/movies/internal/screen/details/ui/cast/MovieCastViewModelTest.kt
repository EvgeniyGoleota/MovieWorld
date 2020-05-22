package com.escorp.movieworld.movies.internal.screen.details.ui.cast

import com.escorp.movieworld.core.data.api.dto.movies.Cast
import com.escorp.movieworld.movies.internal.screen.details.domain.GetMovieCastUseCase
import com.escorp.movieworld.test_unit_base.AndroidArchComponentExtension
import com.escorp.movieworld.test_unit_base.RxJavaExtension
import com.escorp.movieworld.test_unit_base.test
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Extensions(
    ExtendWith(AndroidArchComponentExtension::class),
    ExtendWith(RxJavaExtension::class)
)
internal class MovieCastViewModelTest {
    private val TEST_MOVIE_ID = Int.MAX_VALUE

    private val getMovieCastUseCaseMock = mock<GetMovieCastUseCase>()

    private val movieCastViewModel = MovieCastViewModel(getMovieCastUseCaseMock)

    @Test
    fun getMovieCast() {
        val fakeCast = mock<Cast>()
        whenever(getMovieCastUseCaseMock.invoke(any())).thenReturn(Single.just(listOf(fakeCast)))

        movieCastViewModel.getMovieCast(TEST_MOVIE_ID)
        movieCastViewModel.movieCast.test().assertValue { it.size == 1 }

        verify(getMovieCastUseCaseMock).invoke(TEST_MOVIE_ID)
        verifyNoMoreInteractions(getMovieCastUseCaseMock)
    }
}