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

//    var movieList: ArrayList<MovieResult> = arrayListOf<MovieResult>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    private var movies: MutableList<MovieResult> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovie {
        return ViewHolderMovie(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderMovie, position: Int) {

        holder.tittle.text = movies[position].title
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w1280/" + movies[position].backdrop_path)
            .placeholder(R.drawable.icons8_placeholder)
            .into(holder.poster)
        holder.voteAverage.text = movies[position].vote_average.toString()


    }

    override fun getItemCount(): Int {
        return movies.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(items: ArrayList<MovieResult>) {
        movies.addAll(items)
        notifyItemRangeInserted(
            this.movies.size,
            movies.size - 1 ) }


    }



class ViewHolderMovie(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tittle = itemView.tvTitleMovieItem
    val poster = itemView.ivPoster
    val voteAverage = itemView.ivVoteAverage

}
