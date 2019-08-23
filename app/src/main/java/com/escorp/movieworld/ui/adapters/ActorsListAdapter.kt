package com.escorp.movieworld.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.escorp.movieworld.data.api.models.Actor
import com.escorp.movieworld.data.api.models.Movie
import com.escorp.movieworld.databinding.ActorListItemBinding

class ActorsListAdapter : PagedListAdapter<Actor, ActorsListAdapter.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Actor>() {
            override fun areItemsTheSame(oldItem: Actor, newItem: Actor) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Actor, newItem: Actor) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ActorListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class ViewHolder(private val binding: ActorListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(actor: Actor?) {
            actor?.let { binding.actor = it }
        }
    }
}