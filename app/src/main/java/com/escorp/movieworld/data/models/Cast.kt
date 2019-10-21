package com.escorp.movieworld.data.models

import com.google.gson.annotations.SerializedName

data class Cast(
    val id: Int,

    @SerializedName("cast_id")
    val castId: Int,

    val character: String,

    @SerializedName("credit_id")
    val creditId: String,

    val gender: Int?,

    val name: String,

    val order: Int,

    @SerializedName("profile_path")
    var profilePath: String?
) : Comparable<Cast> {
    fun getFormattedProfilePath(): String? {
        if (profilePath?.startsWith("http") == false) {
            profilePath = String.format("https://image.tmdb.org/t/p/w500%s", profilePath)
        }
        return profilePath
    }

    override fun compareTo(other: Cast) = order.compareTo(other.order)
}
