package com.escorp.movieworld.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.escorp.movieworld.data.api.MovieApi
import com.escorp.movieworld.data.models.*
import com.escorp.movieworld.data.db.DatabaseDao
import com.escorp.movieworld.utils.defaultPageSize
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class DataRepository(private val movieApi: MovieApi, private val databaseDao: DatabaseDao) {

    fun getTopRatedMovies(page: Int) = movieApi.getTopRatedMovies(page)

    fun getPopularMovies(page: Int) = movieApi.getPopularMovies(page)

    fun getPopularPeople(page: Int) = movieApi.getPopularPeople(page)

    fun getPersonDetails(personId: Long) = movieApi.getPersonDetail(personId)

    fun getPersonPhotos(personId: Long): LiveData<APhotoResponse> =
        LiveDataReactiveStreams.fromPublisher(movieApi.getPersonPhotos(personId)
            .doOnError { error ->
                error.printStackTrace()
                Log.e("MW:::", "Network error while receiving person's photo: ${error.message}")
            }
            .onErrorReturnItem(APhotoResponse(-1, emptyList()))
            .subscribeOn(Schedulers.io()))

    fun getPersonCredits(personId: Long): LiveData<ACreditsResponse> =
        LiveDataReactiveStreams.fromPublisher(movieApi.getPersonsCombinedCredits(personId)
            .doOnError { error ->
                error.printStackTrace()
                Log.e("MW:::", "Network error while receiving person's credits: ${error.message}")
            }
            .onErrorReturnItem(ACreditsResponse(-1, emptyList()))
            .subscribeOn(Schedulers.io()))

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

    fun saveMoviesToCash(list: List<Movie>) {
        databaseDao.insertMovies(list)
    }

    fun clearMoviesCash() {
        databaseDao.clearMovieCash()
    }

    fun saveActorsToCash(list: List<Actor>) {
        databaseDao.insertActors(list)
    }

    fun clearActorsCash() {
        databaseDao.clearActorsCash()
    }
}