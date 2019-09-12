package com.escorp.movieworld.data.models

data class APhotoResponse(
    val id: Int,
    val profiles: List<Image>
) {
    fun isValid() = id != -1 && profiles.isNotEmpty()
}