package com.escorp.movieworld.actors.list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.escorp.movieworld.core.databinding.ActorListItemBinding
import com.escorp.movieworld.core.ui.RecyclerViewOnItemClickListener
import com.escorp.movieworld.network.api.dto.actors.Actor

class ActorsListAdapter : PagedListAdapter<Actor, ActorsListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Actor>() {
        override fun areItemsTheSame(oldItem: Actor, newItem: Actor) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Actor, newItem: Actor) = oldItem == newItem
    }
) {

    var onItemClickListener: RecyclerViewOnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ActorListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class ViewHolder(private val binding: ActorListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(actor: Actor?) {
            actor?.let { binding.actor = it }
            onItemClickListener?.let { binding.onClickListener = it }
        }
    }
}