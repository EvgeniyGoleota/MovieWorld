package com.escorp.movieworld.data.models

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Actor(
    @PrimaryKey(autoGenerate = true)
    val dbId: Int,

    val id: Int,

    @SerializedName("profile_path")
    var profilePath: String?,

    var name: String?,

    var popularity: Double?,

    @SerializedName("known_for")
    var knownFor: List<Movie>?
) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Actor>() {
            override fun areItemsTheSame(oldItem: Actor, newItem: Actor) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Actor, newItem: Actor) = oldItem == newItem
        }
    }

    fun getFormattedPosterPath(): String? {
        if (profilePath != null && !profilePath!!.startsWith("http")) {
            profilePath = String.format("https://image.tmdb.org/t/p/w500%s", profilePath)
        }
        return profilePath
    }
}