package com.escorp.movieworld.core.data.api.dto.common

data class CreditsResponse<T>(

    val id: Int,

    val cast: List<T>

//  val crew: List<Crew>
) {
    constructor(): this(-1, emptyList())
}