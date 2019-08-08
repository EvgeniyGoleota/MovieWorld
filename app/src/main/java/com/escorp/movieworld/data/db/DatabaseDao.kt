package com.escorp.movieworld.data.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.escorp.movieworld.data.api.models.Actor
import com.escorp.movieworld.data.api.models.Movie

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActors(actors: List<Actor>)

    @Query("SELECT * FROM Actor")
    fun selectActorsPaged(): DataSource.Factory<Int, Actor>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM Movie")
    fun selectMoviesPaged(): DataSource.Factory<Int, Movie>
}