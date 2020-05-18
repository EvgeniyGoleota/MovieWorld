package com.escorp.movieworld.core.data.api.dto.actors

import com.escorp.movieworld.core.data.api.dto.movies.Movie
import com.google.gson.annotations.SerializedName

data class Actor(
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