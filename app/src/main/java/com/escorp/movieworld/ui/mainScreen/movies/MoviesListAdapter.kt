package com.escorp.movieworld.ui.mainScreen.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.escorp.movieworld.data.models.Movie
import com.escorp.movieworld.databinding.MovieListItemBinding
import com.escorp.movieworld.ui.uiUtils.RecyclerViewOnItemClickListener

class MoviesListAdapter : PagedListAdapter<Movie, MoviesListAdapter.ViewHolder>(Movie.diffCallback) {

    var onItemClickListener: RecyclerViewOnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class ViewHolder(private val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(movie: Movie?) {
            movie?.let { binding.movie = movie }
            onItemClickListener?.let {
                binding.onClickListener = it
            }
        }
    }
}