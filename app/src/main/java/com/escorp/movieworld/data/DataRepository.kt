package com.escorp.movieworld.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.escorp.movieworld.data.dataSources.factories.SimilarMovieDataSourceFactory
import com.escorp.movieworld.data.models.*
import io.reactivex.Observable

interface DataRepository {

    fun getTopRatedMovies(page: Int): Observable<Response<Movie>>

    fun getPopularMovies(page: Int): Observable<Response<Movie>>

    fun getPopularPeople(page: Int): Observable<Response<Actor>>

    fun getPersonDetails(personId: Int): Observable<ActorDetail>

    fun getPersonPhotos(personId: Int): LiveData<List<Image>>

    fun getPersonCredits(personId: Int): LiveData<List<Movie>>

    fun getMovieCredits(movieId: Int): LiveData<List<Cast>>

    fun getSimilarMovies(movieId: Int, page: Int): Observable<Response<Movie>>

    fun getMovieDetail(movieId: Int): Observable<MovieDetail>

    fun getPagedMovieListLiveData(): LiveData<PagedList<Movie>>

    fun getPagedSimilarMoviesListLiveData(dataSourceFactory: SimilarMovieDataSourceFactory): LiveData<PagedList<Movie>>

    fun getPagedActorListLiveData(): LiveData<PagedList<Actor>>

    fun saveMoviesToCash(list: List<Movie>)

    fun clearMoviesCash()

    fun saveActorsToCash(list: List<Actor>)

    fun clearActorsCash()
}