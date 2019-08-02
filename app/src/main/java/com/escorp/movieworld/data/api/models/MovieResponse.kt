package com.escorp.movieworld.data.api.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page: Long,

    val results: List<Movie>,

    @SerializedName("total_results")
    val totalResults: Long,

    @SerializedName("total_pages")
    val totalPages: Long
) {
    constructor(): this(0, emptyList(), 0, 0)
}