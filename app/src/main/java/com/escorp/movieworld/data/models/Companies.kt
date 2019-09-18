package com.escorp.movieworld.data.models

import com.google.gson.annotations.SerializedName

data class Companies(
    val id: Int,
    val name: String,
    @SerializedName("logo_path")
    var logo: String?,
    @SerializedName("origin_country")
    val country: String
)