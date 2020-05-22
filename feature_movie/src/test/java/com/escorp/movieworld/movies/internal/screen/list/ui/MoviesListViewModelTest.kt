package com.escorp.movieworld.movies.internal.screen.list.ui

import androidx.lifecycle.MutableLiveData
import com.escorp.movieworld.core.data.api.dto.movies.Movie
import com.escorp.movieworld.movies.internal.screen.list.domain.GetPagedPopularMoviesLiveDataUseCase
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
internal class MoviesListViewModelTest {

    private val movieMock = mock<Movie>()
    private val pagedList = mockPagedList(listOf(movieMock))

    private val getPagedPopularMoviesLiveDataUseCaseMock = mock<GetPagedPopularMoviesLiveDataUseCase>()
    private val compositeDisposableMock = mock<CompositeDisposable>()

    private lateinit var movieListViewModel: MoviesListViewModel

    @BeforeEach
    fun setup() {
        whenever(getPagedPopularMoviesLiveDataUseCaseMock.invoke(any())).thenReturn( MutableLiveData(pagedList))
        movieListViewModel = MoviesListViewModel(getPagedPopularMoviesLiveDataUseCaseMock, compositeDisposableMock)
    }

    @Test
    fun getPopularMovies() {
        movieListViewModel.getPopularMovies().test().assertValue { it.size == pagedList.size }

        verify(getPagedPopularMoviesLiveDataUseCaseMock).invoke(compositeDisposableMock)
        verifyNoMoreInteractions(getPagedPopularMoviesLiveDataUseCaseMock)
    }
}