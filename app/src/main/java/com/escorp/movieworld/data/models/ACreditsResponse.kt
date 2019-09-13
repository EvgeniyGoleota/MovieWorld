package com.escorp.movieworld.data.models

data class ACreditsResponse(

    val id: Int,

    val cast: List<Movie>

//todo Add crew field
) {
    fun isValid() = id != -1 && cast.isNotEmpty()
}