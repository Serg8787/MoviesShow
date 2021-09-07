package com.example.destination.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.destination.MainActivity
import com.example.destination.R
import com.example.destination.model.MovieResult
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.coroutines.withContext

class MovieAdapter :RecyclerView.Adapter<ViewHolderMovie>(){

      var movieList: ArrayList<MovieResult> = arrayListOf<MovieResult>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovie {
        return ViewHolderMovie(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderMovie, position: Int) {

            holder.tittle.text = movieList[position].title
            Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w1280/"+movieList[position].backdrop_path).placeholder(R.drawable.icons8_placeholder)
                .into(holder.poster)
            holder.voteAverage.text = movieList[position].vote_average.toString()
    }

    override fun getItemCount(): Int {
       return movieList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addList(items:ArrayList<MovieResult>){
        movieList.clear()
        movieList.addAll(items)
        notifyDataSetChanged()

    }
    @SuppressLint("NotifyDataSetChanged")
    fun clear(){
        movieList.clear()
        notifyDataSetChanged()
    }
}
class ViewHolderMovie(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tittle = itemView.tvTitleMovieItem
    val poster = itemView.ivPoster
    val voteAverage = itemView.ivVoteAverage

}
