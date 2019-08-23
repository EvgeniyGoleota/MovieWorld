package com.escorp.movieworld.data.db

import androidx.paging.DataSource
import androidx.room.*
import com.escorp.movieworld.data.api.models.Actor
import com.escorp.movieworld.data.api.models.Movie

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActors(actors: List<Actor>)

    @Query("SELECT * FROM Actor")
    fun selectActorsPaged(): DataSource.Factory<Int, Actor>

    @Query("DELETE FROM Actor")
    fun clearActorsCash()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM Movie")
    fun selectMoviesPaged(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM Movie")
    fun selectMovies():List<Movie>

    @Query("DELETE FROM Movie")
    fun clearMovieCash()
}