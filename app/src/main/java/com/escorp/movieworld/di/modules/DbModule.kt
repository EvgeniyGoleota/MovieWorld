package com.escorp.movieworld.di.modules

import androidx.room.Room
import com.escorp.movieworld.Application
import com.escorp.movieworld.data.db.AppDatabase
import com.escorp.movieworld.data.db.DatabaseDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    internal fun providesAppDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "MovieWorldAppDatabase"
        ).allowMainThreadQueries().build()

    @Provides
    @Singleton
    internal fun providesDatabaseDao(appDatabase: AppDatabase): DatabaseDao = appDatabase.databaseDao()
}