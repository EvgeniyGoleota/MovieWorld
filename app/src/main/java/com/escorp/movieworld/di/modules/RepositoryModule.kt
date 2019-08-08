package com.escorp.movieworld.di.modules

import com.escorp.movieworld.data.DataRepository
import com.escorp.movieworld.data.api.MovieApi
import com.escorp.movieworld.data.db.DatabaseDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun providesDataRepository(movieApi: MovieApi, databaseDao: DatabaseDao): DataRepository =
        DataRepository(movieApi, databaseDao)
}