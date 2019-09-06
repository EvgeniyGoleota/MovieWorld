package com.escorp.movieworld.data.api.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
data class Movie(
    val id: Long,

    @SerializedName(value = "header", alternate = ["title", "name"])
    val header: String?,

    @SerializedName("poster_path")
    var posterPath: String?,

    @SerializedName(value = "description", alternate = ["overview", "synopsis"])
    var description: String?,

    @SerializedName("release_date")
    var releaseDate: String?,

    @SerializedName("original_title")
    var originalTitle: String?,

    @SerializedName("genre_ids")
    var genreIds: List<Int>?,

    @SerializedName("original_language")
    var originalLanguage: String?,

    @SerializedName("media_type")
    var mediaType: String?,

    @SerializedName("runtime")
    var runTime: Long?,

    var status: String?,

    var popularity: Double?,

    @SerializedName("vote_count")
    var voteCount: Int?,

    var video: Boolean?,

    @SerializedName("vote_average")
    var voteAverage: Double?,

//----------------------------------------------------For tv media type-------------------------------------------------
    @SerializedName("first_air_date")
    var firstAirDate: String?,

    @SerializedName("origin_country")
    var originCountry: List<String>?
) {
    fun getFormattedPosterPath(): String? {
        if (posterPath != null && !posterPath!!.startsWith("http")) {
            posterPath = String.format("https://image.tmdb.org/t/p/w500%s", posterPath)
        }
        return posterPath
    }
}