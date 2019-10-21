package com.escorp.movieworld.ui.detailScreen.movie.cast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.escorp.movieworld.data.models.Cast
import com.escorp.movieworld.databinding.MovieCastListItemBinding
import com.escorp.movieworld.ui.uiUtils.RecyclerViewOnItemClickListener

class MovieCastListAdapter : RecyclerView.Adapter<MovieCastListAdapter.ViewHolder>() {

    private val casts =  mutableListOf<Cast>()

    var onItemClickListener: RecyclerViewOnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(MovieCastListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = casts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(casts[position])
    }

    fun setItems(casts: List<Cast>) {
        this.casts.apply {
            clear()
            addAll(casts)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: MovieCastListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(cast: Cast) {
            binding.apply {
                this.cast = cast
                onItemClickListener?.let {
                    onClickListener = onItemClickListener
                }
            }
        }
    }
}