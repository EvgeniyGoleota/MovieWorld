package com.escorp.movieworld.ui.actorsScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.escorp.movieworld.data.models.Actor
import com.escorp.movieworld.databinding.ActorListItemBinding
import com.escorp.movieworld.ui.interfaces.RecyclerViewOnItemClickListener

class ActorsListAdapter : PagedListAdapter<Actor, ActorsListAdapter.ViewHolder>(Actor.diffCallback) {

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