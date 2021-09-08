package com.example.destination.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.destination.R
import com.example.destination.model.MovieResult
import kotlinx.android.synthetic.main.item_loading_movie.view.*
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter : RecyclerView.Adapter<ViewHolderMovie>() {

    var movieList: ArrayList<MovieResult> = arrayListOf<MovieResult>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onBottomReachedListener: OnBottomReachedListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovie {
        return ViewHolderMovie(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderMovie, position: Int) {

        holder.tittle.text = movieList[position].title
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w1280/" + movieList[position].backdrop_path)
            .placeholder(R.drawable.icons8_placeholder)
            .into(holder.poster)
        holder.voteAverage.text = movieList[position].vote_average.toString()

        if (position == movieList.size - 5){
            onBottomReachedListener?.onBottomReached(position);
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(items: ArrayList<MovieResult>) {
        movieList.addAll(items)
        notifyItemRangeInserted(
            this.movieList.size,
            movieList.size - 1 ) }


    }



class ViewHolderMovie(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tittle = itemView.tvTitleMovieItem
    val poster = itemView.ivPoster
    val voteAverage = itemView.ivVoteAverage

}

class ViewHolderMovieProgressBar(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val progressBar = itemView.progress_bar_movie
}

interface OnBottomReachedListener {
    fun onBottomReached(position: Int)
}
