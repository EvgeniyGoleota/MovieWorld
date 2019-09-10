package com.escorp.movieworld.data.db

import androidx.room.TypeConverter
import com.escorp.movieworld.data.models.Actor
import com.escorp.movieworld.data.models.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun movieListToString(list: List<Movie>?): String = (Gson()).toJson(list)

    @TypeConverter
    fun stringToMovieList(string: String?) : List<Movie> {
        if (string == null || string == "null") return emptyList()
        val listType = object : TypeToken<List<Movie>>() {}.type
        return (Gson()).fromJson(string, listType)
    }

    @TypeConverter
    fun actorListToString(list: List<Actor>?): String = (Gson()).toJson(list)

    @TypeConverter
    fun stringToActorList(string: String?) : List<Actor> {
        if (string == null || string == "null") return emptyList()
        val listType = object : TypeToken<List<Actor>>() {}.type
        return (Gson()).fromJson(string, listType)
    }

    @TypeConverter
    fun genreListToString(list: List<Int>?): String = (Gson()).toJson(list)

    @TypeConverter
    fun stringToGenreList(string: String?) : List<Int> {
        if (string == null || string == "null") return emptyList()
        val listType = object : TypeToken<List<Int>>() {}.type
        return (Gson()).fromJson(string, listType)
    }

    @TypeConverter
    fun countryListToString(list: List<String>?): String = (Gson()).toJson(list)

    @TypeConverter
    fun stringToCountryList(string: String?) : List<String> {
        if (string == null || string == "null") return emptyList()
        val listType = object : TypeToken<List<String>>() {}.type
        return (Gson()).fromJson(string, listType)
    }
}