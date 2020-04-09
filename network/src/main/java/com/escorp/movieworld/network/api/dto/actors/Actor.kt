package com.escorp.movieworld.network.api.dto.actors

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.escorp.movieworld.network.api.dto.movies.Movie
import com.google.gson.annotations.SerializedName

@Entity
data class Actor(
    @PrimaryKey(autoGenerate = true)
    val dbId: Int,

    val id: Int,

    @SerializedName("profile_path")
    var profilePath: String?,

    var name: String?,

    var popularity: Double?,

    @SerializedName("known_for")
    var knownFor: List<Movie>?
) {
    fun getFormattedPosterPath(): String? {
        if (profilePath?.startsWith("http") == false) {
            profilePath = String.format("https://image.tmdb.org/t/p/w500%s", profilePath)
        }
        return profilePath
    }
}