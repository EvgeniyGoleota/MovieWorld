package com.escorp.movieworld.network.api.dto.movies

import com.escorp.movieworld.network.api.dto.common.Collection
import com.escorp.movieworld.network.api.dto.common.Companies
import com.escorp.movieworld.network.api.dto.common.Country
import com.escorp.movieworld.network.api.dto.common.Genre
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class MovieDetail(
    val id: Int,

    @SerializedName("backdrop_path")
    var backdropPath: String?,

    @SerializedName("belongs_to_collection")
    val belongToCollection: Collection?,

    val budget: Int,

    val genres: List<Genre>,

    val homepage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    val overview: String?,

    val popularity: Double,

    @SerializedName("poster_path")
    var posterPath: String?,

    @SerializedName("production_companies")
    val productionCompanies: List<Companies>,

    @SerializedName("production_countries")
    val productionCountries: List<Country>,

    @SerializedName("release_date")
    val releaseDate: Date,

    val revenue: Long,

    val runtime: Int?,

    val status: String,

    @SerializedName("tagline")
    val tagLine: String?,

    val title: String,

    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int
) {
    fun getFormattedPosterPath(): String? {
        if (posterPath?.startsWith("http") == false) {
            posterPath = String.format("https://image.tmdb.org/t/p/w500%s", posterPath)
        }
        return posterPath
    }

    fun getFormattedBackdropPath(): String? {
        if (backdropPath?.startsWith("http") == false) {
            backdropPath = String.format("https://image.tmdb.org/t/p/w500%s", backdropPath)
        }
        return backdropPath
    }

    fun getReleaseDateFormatted(): String
            = SimpleDateFormat("dd MMMM yyyy", Locale.US).format(releaseDate)

    fun getReleaseYear(): String = SimpleDateFormat("yyyy", Locale.US).format(releaseDate)

    fun getCountries(): String {
        var countries = ""
        if (productionCountries.isNotEmpty()) {
            for (country in productionCountries)
                countries += "${country.name}, "
            countries = countries.substring(0, countries.length - 2)
        }
        return countries
    }

    fun getMovieGenres(): String {
        var movieGenres = ""
        if (genres.isNotEmpty()) {
            for (genre in genres)
                movieGenres += "${genre.name}, "
            movieGenres = movieGenres.substring(0, movieGenres.length - 2)
        }
        return movieGenres
    }
}