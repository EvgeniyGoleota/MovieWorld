package com.escorp.movieworld.network.api.dto.common

import com.google.gson.annotations.SerializedName

data class Response<T>(
    val page: Long,

    val results: List<T>,

    @SerializedName("total_results")
    val totalResults: Long,

    @SerializedName("total_pages")
    val totalPages: Long
) {
    constructor(): this(-1, emptyList(), -1, -1)

    fun isSuccessful() = results.isNotEmpty()
}