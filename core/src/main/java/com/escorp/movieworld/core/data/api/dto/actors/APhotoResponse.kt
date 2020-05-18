package com.escorp.movieworld.core.data.api.dto.actors

import com.escorp.movieworld.core.data.api.dto.common.Image

data class APhotoResponse(
    val id: Int,
    val profiles: List<Image>
) {
    constructor(): this(-1, emptyList())
}