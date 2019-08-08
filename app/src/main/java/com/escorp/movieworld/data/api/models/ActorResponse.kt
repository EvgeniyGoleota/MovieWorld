package com.escorp.movieworld.data.api.models

import com.google.gson.annotations.SerializedName

data class ActorResponse(
    val page: Long,

    val results: List<Actor>,

    @SerializedName("total_results")
    val totalResults: Long,

    @SerializedName("total_pages")
    val totalPages: Long
) {
    constructor(): this(-1, emptyList(), -1, -1)

    fun isSuccessful() = results.isNotEmpty()
}