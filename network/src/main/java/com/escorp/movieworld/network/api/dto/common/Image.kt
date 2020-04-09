package com.escorp.movieworld.network.api.dto.common

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double?,

    @SerializedName("file_path")
    var filePath: String?,

    val height: Int?,

    val width: Int?
) {
    fun getFormattedFilePath(): String? {
        if (filePath?.startsWith("http") == false) {
            filePath = String.format("https://image.tmdb.org/t/p/w500%s", filePath)
        }
        return filePath
    }
}