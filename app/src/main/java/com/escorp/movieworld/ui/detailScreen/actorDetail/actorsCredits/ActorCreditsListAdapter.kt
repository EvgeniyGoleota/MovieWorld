package com.escorp.movieworld.ui.detailScreen.actorDetail.actorsCredits

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.escorp.movieworld.data.models.Movie
import com.escorp.movieworld.databinding.MovieListItemBinding
import com.escorp.movieworld.utils.interfaces.RecyclerViewOnItemClickListener

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

    inner class ViewHolder(val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(movie: Movie) {
            binding.movie = movie
            onClickListener?.let {
                binding.onClickListener = it
            }
        }
    }
}