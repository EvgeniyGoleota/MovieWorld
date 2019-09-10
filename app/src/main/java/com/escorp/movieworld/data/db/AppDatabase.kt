package com.escorp.movieworld.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.escorp.movieworld.data.models.Actor
import com.escorp.movieworld.data.models.Movie

@Database(entities = [Actor::class, Movie::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun databaseDao(): DatabaseDao
}