package com.escorp.movieworld.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.escorp.movieworld.data.api.MovieApi
import com.escorp.movieworld.data.dataSources.factories.SimilarMovieDataSourceFactory
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

    fun getPersonDetails(personId: Int) = movieApi.getPersonDetail(personId)

    fun getPersonPhotos(personId: Int): LiveData<APhotoResponse> =
        LiveDataReactiveStreams.fromPublisher(movieApi.getPersonPhotos(personId)
            .doOnError { error ->
                error.printStackTrace()
                Log.e("MW:::", "Network error while receiving person's photo: ${error.message}")
            }
            .onErrorReturnItem(APhotoResponse(-1, emptyList()))
            .subscribeOn(Schedulers.io()))

    fun getPersonCredits(personId: Int): LiveData<ACreditsResponse> =
        LiveDataReactiveStreams.fromPublisher(movieApi.getPersonsCombinedCredits(personId)
            .doOnError { error ->
                error.printStackTrace()
                Log.e(
                    "MW:::",
                    "Network error while receiving person's credits: ${error.message}"
                )
            }
            .onErrorReturnItem(ACreditsResponse(-1, emptyList()))
            .subscribeOn(Schedulers.io()))

    fun getMovieCredits(movieId: Int): LiveData<List<Cast>> =
        LiveDataReactiveStreams.fromPublisher(movieApi.getMovieCredits(movieId)
            .doOnError { error ->
                error.printStackTrace()
                Log.e(
                    "MW:::",
                    "Network error while receiving movie's credits: ${error.message}"
                )
            }
            .onErrorReturnItem(MCreditsResponse(-1, emptyList()))
            .map { response ->
                return@map response.cast
            }
            .subscribeOn(Schedulers.io()))

    fun getSimilarMovies(movieId: Int, page: Int) = movieApi.getSimilarMovies(movieId, page)

    fun getMovieDetail(movieId: Int) = movieApi.getMovieDetail(movieId)

    fun getPagedMovieListLiveData(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setPageSize(defaultPageSize)
            .setInitialLoadSizeHint(defaultPageSize)
            .build()
        return LivePagedListBuilder(databaseDao.selectMoviesPaged(), config).build()
    }

    fun getPagedSimilarMoviesListLiveData(dataSourceFactory: SimilarMovieDataSourceFactory): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setPageSize(defaultPageSize)
            .setInitialLoadSizeHint(defaultPageSize)
            .build()
        return LivePagedListBuilder(dataSourceFactory, config).build()
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