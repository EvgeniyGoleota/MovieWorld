package com.escorp.movieworld.network.api.dto.common

import com.google.gson.annotations.SerializedName

data class Collection(
    val id: Int,

    val name: String,

    @SerializedName("poster_path")
    var posterPath: String?,

    @SerializedName("backdrop_path")
    var backdropPath: String?
)