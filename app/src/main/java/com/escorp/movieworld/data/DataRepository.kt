package com.escorp.movieworld.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.escorp.movieworld.data.api.MovieApi
import com.escorp.movieworld.data.api.models.*
import com.escorp.movieworld.data.db.DatabaseDao
import com.escorp.movieworld.utils.defaultPageSize
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class DataRepository(private val movieApi: MovieApi, private val databaseDao: DatabaseDao) {

    fun getTopRatedMovies(page: Int): LiveData<Response> =
        LiveDataReactiveStreams.fromPublisher(movieApi.getTopRatedMovies(page)
            .subscribeOn(Schedulers.io())
            .doOnError { error ->
                error.printStackTrace()
                Log.e("MW:::", "Network error while receiving top rated movies: ${error.message}")
            }
            .onErrorReturnItem(MovieResponse())
            .map { movieResponse: MovieResponse ->
                if (movieResponse.isSuccessful()) {
                    if (movieResponse.page == 1L) clearMoviesCash()
                    saveMoviesToCash(movieResponse.results)
                    return@map Response(movieResponse.page, movieResponse.totalResults, movieResponse.totalPages, true)
                } else {
                    return@map Response(false)
                }
            })

    fun getPopularPeople(page: Int) = movieApi.getPopularPeople(page)

    fun getPagedMovieListLiveData(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setPageSize(defaultPageSize)
            .setInitialLoadSizeHint(defaultPageSize)
            .build()
        return LivePagedListBuilder(databaseDao.selectMoviesPaged(), config).build()
    }

    fun getPagedActorListLiveData(): LiveData<PagedList<Actor>> {
        val config = PagedList.Config.Builder()
            .setPageSize(defaultPageSize)
            .setInitialLoadSizeHint(defaultPageSize)
            .build()
        return LivePagedListBuilder(databaseDao.selectActorsPaged(), config).build()
    }

    private fun saveMoviesToCash(list: List<Movie>) {
        databaseDao.insertMovies(list)
    }

    private fun clearMoviesCash() {
        databaseDao.clearMovieCash()
    }

    fun saveActorsToCash(list: List<Actor>) {
        databaseDao.insertActors(list)
    }

    fun clearActorsCash() {
        databaseDao.clearActorsCash()
    }
}