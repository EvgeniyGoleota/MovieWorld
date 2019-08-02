package com.escorp.movieworld.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.escorp.movieworld.data.api.models.People
import com.escorp.movieworld.databinding.ActorListItemBinding

class ActorsListAdapter : RecyclerView.Adapter<ActorsListAdapter.ViewHolder>() {
    private var actors: MutableList<People> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ActorListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    fun setItems(actors: List<People>) {
        val startPosition = this.actors.size
        this.actors.addAll(actors)
        notifyItemRangeChanged(startPosition, this.actors.size)
    }

    private fun getItem(position: Int) = actors[position]

    class ViewHolder(private val binding: ActorListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(actor: People) {
            binding.actor = actor
        }
    }
}