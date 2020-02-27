package com.escorp.movieworld.di.modules

import androidx.room.Room
import com.escorp.movieworld.MovieWorldApplication
import com.escorp.movieworld.data.db.AppDatabase
import com.escorp.movieworld.data.db.TMDbDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    internal fun providesAppDatabase(application: MovieWorldApplication): AppDatabase =
        Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "MovieWorldAppDatabase"
        ).allowMainThreadQueries().build()

    @Provides
    @Singleton
    internal fun providesDatabaseDao(appDatabase: AppDatabase): TMDbDao =
        appDatabase.databaseDao()
}