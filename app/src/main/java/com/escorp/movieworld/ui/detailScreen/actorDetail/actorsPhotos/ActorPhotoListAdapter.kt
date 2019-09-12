package com.escorp.movieworld.ui.detailScreen.actorDetail.actorsPhotos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.escorp.movieworld.data.models.Image
import com.escorp.movieworld.databinding.PhotoListItemBinding
import com.escorp.movieworld.ui.interfaces.RecyclerViewOnItemClickListener

class ActorPhotoListAdapter : RecyclerView.Adapter<ActorPhotoListAdapter.ViewHolder>() {

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

    inner class ViewHolder(val binding: PhotoListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(photo: Image) {
            binding.photo = photo
            onItemClickListener?.let {
                binding.clickListener = it
            }
        }
    }
}