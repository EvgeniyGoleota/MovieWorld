package com.escorp.movieworld.data.models

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val dbId: Int,

    val id: Int,

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
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
        }
    }

    fun getFormattedPosterPath(): String? {
        if (posterPath != null && !posterPath!!.startsWith("http")) {
            posterPath = String.format("https://image.tmdb.org/t/p/w500%s", posterPath)
        }
        return posterPath
    }
}