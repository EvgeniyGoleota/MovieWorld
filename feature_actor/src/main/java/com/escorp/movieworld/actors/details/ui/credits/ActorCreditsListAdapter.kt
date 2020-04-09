package com.escorp.movieworld.actors.details.ui.credits

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.escorp.movieworld.core.databinding.MovieListItemBinding
import com.escorp.movieworld.core.ui.RecyclerViewOnItemClickListener
import com.escorp.movieworld.network.api.dto.movies.Movie

class ActorCreditsListAdapter : RecyclerView.Adapter<ActorCreditsListAdapter.ViewHolder>() {

    private val credits = mutableListOf<Movie>()

    var onClickListener: RecyclerViewOnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = credits.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(credits[position])
    }

    fun setItems(credits: List<Movie>) {
        this.credits.apply {
            clear()
            addAll(credits)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(movie: Movie) {
            binding.movie = movie
            onClickListener?.let {
                binding.onClickListener = it
            }
        }
    }
}