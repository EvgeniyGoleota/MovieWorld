package com.escorp.movieworld.di.modules

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.api.MovieApi
import com.escorp.movieworld.data.dataSources.factories.SimilarMovieDataSourceFactory
import com.escorp.movieworld.data.db.TMDbDao
import com.escorp.movieworld.data.models.*
import com.escorp.movieworld.utils.defaultPageSize
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers

@Module
class RepositoryModule {

    @Provides
    internal fun providesDataRepository(
        movieApi: MovieApi,
        databaseDao: TMDbDao
    ): DataRepository = object :
        DataRepository {

        override fun getTopRatedMovies(page: Int) = movieApi.getTopRatedMovies(page)

        override fun getPopularMovies(page: Int) = movieApi.getPopularMovies(page)

        override fun getPopularPeople(page: Int) = movieApi.getPopularPeople(page)

        override fun getPersonDetails(personId: Int) = movieApi.getPersonDetail(personId)

        override fun getPersonPhotos(personId: Int): LiveData<List<Image>> =
            LiveDataReactiveStreams.fromPublisher(movieApi.getPersonPhotos(personId)
                .doOnError { error ->
                    error.printStackTrace()
                    Log.e(
                        "MW:::",
                        "Network error while receiving person's photo: ${error.message}"
                    )
                }
                .onErrorReturnItem(APhotoResponse())
                .map { it.profiles }
                .subscribeOn(Schedulers.io()))

        override fun getPersonCredits(personId: Int): LiveData<List<Movie>> =
            LiveDataReactiveStreams.fromPublisher(movieApi.getPersonsCombinedCredits(personId)
                .doOnError { error ->
                    error.printStackTrace()
                    Log.e(
                        "MW:::",
                        "Network error while receiving person's credits: ${error.message}"
                    )
                }
                .onErrorReturnItem(CreditsResponse())
                .map { it.cast }
                .subscribeOn(Schedulers.io()))

        override fun getMovieCredits(movieId: Int): LiveData<List<Cast>> =
            LiveDataReactiveStreams.fromPublisher(movieApi.getMovieCredits(movieId)
                .doOnError { error ->
                    error.printStackTrace()
                    Log.e(
                        "MW:::",
                        "Network error while receiving movie's credits: ${error.message}"
                    )
                }
                .onErrorReturnItem(CreditsResponse())
                .map { it.cast }
                .subscribeOn(Schedulers.io()))

        override fun getSimilarMovies(movieId: Int, page: Int) =
            movieApi.getSimilarMovies(movieId, page)

        override fun getMovieDetail(movieId: Int) = movieApi.getMovieDetail(movieId)

        override fun getPagedMovieListLiveData(): LiveData<PagedList<Movie>> {
            val config = PagedList.Config.Builder()
                .setPageSize(defaultPageSize)
                .setInitialLoadSizeHint(defaultPageSize)
                .build()
            return LivePagedListBuilder(databaseDao.selectMoviesPaged(), config).build()
        }

        override fun getPagedSimilarMoviesListLiveData(dataSourceFactory: SimilarMovieDataSourceFactory): LiveData<PagedList<Movie>> {
            val config = PagedList.Config.Builder()
                .setPageSize(defaultPageSize)
                .setInitialLoadSizeHint(defaultPageSize)
                .build()
            return LivePagedListBuilder(dataSourceFactory, config).build()
        }

        override fun getPagedActorListLiveData(): LiveData<PagedList<Actor>> {
            val config = PagedList.Config.Builder()
                .setPageSize(defaultPageSize)
                .setInitialLoadSizeHint(defaultPageSize)
                .build()
            return LivePagedListBuilder(databaseDao.selectActorsPaged(), config).build()
        }

        override fun saveMoviesToCash(list: List<Movie>) {
            databaseDao.insertMovies(list)
        }

        override fun clearMoviesCash() {
            databaseDao.clearMovieCash()
        }

        override fun saveActorsToCash(list: List<Actor>) {
            databaseDao.insertActors(list)
        }

        override fun clearActorsCash() {
            databaseDao.clearActorsCash()
        }
    }
}