package com.example.destination.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.destination.R
import com.example.destination.model.movie.MovieResult
import kotlinx.android.synthetic.main.movie_item.view.*
import java.security.AccessController.getContext

class MovieAdapter : RecyclerView.Adapter<ViewHolderMovie>() {

    var movies: ArrayList<MovieResult> = arrayListOf<MovieResult>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    val lastPosition=6




    var onClickMovieItem: OnClickMovieItem? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovie {
        return ViewHolderMovie(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderMovie, position: Int) {
        if(holder.adapterPosition>lastPosition) {
            holder.root.startAnimation(
                AnimationUtils.loadAnimation(
                    holder.root.context,
                    R.anim.recycler_view_animate
                )
            )
        }
        holder.tittleMovie.text = movies[position].title
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w1280/" + movies[position].backdrop_path)
            .placeholder(R.drawable.icons8_placeholder)
            .into(holder.posterMovie)
        holder.voteAverageMovie.text = movies[position].vote_average.toString()
        holder.root.setOnClickListener { onClickMovieItem?.itemMovieSelected(movies[position]) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(itemsMovies: List<MovieResult>) {
        movies.addAll(itemsMovies)
        notifyItemRangeInserted(
            this.movies.size,
            movies.size - 1
        )
    }
}

class ViewHolderMovie(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tittleMovie = itemView.tvTitleMovieItem
    val posterMovie = itemView.ivPosterMovie
    val voteAverageMovie = itemView.ivVoteAverageMovie
    val root = itemView.clMovieRoot

}

interface OnClickMovieItem {
    fun itemMovieSelected(movieResult: MovieResult)
}
