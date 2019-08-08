package com.escorp.movieworld.data.api.models

data class Response(
    val page: Long,
    val totalResults: Long,
    val totalPages: Long,
    val isSuccessful: Boolean
) {
    constructor(isSuccessful: Boolean): this(-1, -1, -1, isSuccessful)
}