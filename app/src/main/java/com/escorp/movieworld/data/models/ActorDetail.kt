package com.escorp.movieworld.data.models

import com.google.gson.annotations.SerializedName

data class ActorDetail(
    val id: Long,

    @SerializedName("profile_path")
    var profilePath: String?,

    var name: String?,

    var popularity: Double?,

    var birthday: String?,

    var known_for_department: String,

    var deathday: String?,

    var gender: Int,

    var biography: String,

    @SerializedName("place_of_birth")
    var placeOfBirth: String?,

    var homepage: String?
) {
    fun getFormattedProfilePath(): String? {
        if (profilePath?.startsWith("http") == false) {
            profilePath = String.format("https://image.tmdb.org/t/p/w500%s", profilePath)
        }
        return profilePath
    }
}