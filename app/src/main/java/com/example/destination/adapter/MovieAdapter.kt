package com.example.destination.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.destination.R
import com.example.destination.model.MovieResult
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(val context: Context,val movieList:ArrayList<MovieResult>) :RecyclerView.Adapter<ViewHolderMovie>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovie {
        return ViewHolderMovie(
            LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderMovie, position: Int) {
        holder.tittle.text = movieList[position].title
        Glide.with(context).load("https://image.tmdb.org/t/p/w1280/"+movieList[position].backdrop_path).placeholder(android.R.drawable.btn_default)
            .into(holder.poster)
        holder.voteAverage.text = movieList[position].vote_average.toString()
    }

    override fun getItemCount(): Int {
       return movieList.size
    }
}
class ViewHolderMovie(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tittle = itemView.tvTitleMovieItem
    val poster = itemView.ivPoster
    val voteAverage = itemView.ivVoteAverage

}