package com.escorp.movieworld.actors.internal.screen.details.ui.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.escorp.movieworld.actors.databinding.PhotoListItemBinding
import com.escorp.movieworld.core.ui.RecyclerViewOnItemClickListener
import com.escorp.movieworld.core.data.api.dto.common.Image

internal class ActorPhotoListAdapter : RecyclerView.Adapter<ActorPhotoListAdapter.ViewHolder>() {

    private var photos: MutableList<Image> = mutableListOf()
    var onItemClickListener: RecyclerViewOnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(PhotoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(photos[position])
    }

    fun setItems(photos: List<Image>) {
        this.photos.clear()
        this.photos.addAll(photos)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: PhotoListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(photo: Image) {
            binding.photo = photo
            onItemClickListener?.let {
                binding.clickListener = it
            }
        }
    }
}