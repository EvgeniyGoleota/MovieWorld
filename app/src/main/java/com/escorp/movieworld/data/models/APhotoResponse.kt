package com.escorp.movieworld.data.models

data class APhotoResponse(
    val id: Int,
    val profiles: List<Image>
) {
    constructor(): this(-1, emptyList())
}