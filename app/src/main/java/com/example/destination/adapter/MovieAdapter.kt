package com.example.destination.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        holder.overview.text = movieList[position].overview
    }

    override fun getItemCount(): Int {
       return movieList.size
    }
}
class ViewHolderMovie(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tittle = itemView.tvTitleMovieItem
    val overview = itemView.tvOverviewMovieItem

}