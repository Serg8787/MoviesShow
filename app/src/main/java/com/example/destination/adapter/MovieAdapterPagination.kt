package com.example.destination.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.destination.R
import com.example.destination.model.MovieResult
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapterPagination :  PagingDataAdapter<MovieResult, MovieAdapterPagination.MyViewHolder>(DiffUtilCallback()) {
    override fun onBindViewHolder(holder: MovieAdapterPagination.MyViewHolder, position: Int) {
        holder.tittle.text = getItem(position)?.title
        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w1280/"+getItem(position)?.backdrop_path).placeholder(R.drawable.icons8_placeholder)
            .into(holder.poster)
        holder.voteAverage.text = getItem(position)?.vote_average.toString()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapterPagination.MyViewHolder {
        val inflater =  LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)

        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tittle = itemView.tvTitleMovieItem
        val poster = itemView.ivPoster
        val voteAverage = itemView.ivVoteAverage
    }

    class  DiffUtilCallback: DiffUtil.ItemCallback<MovieResult>() {
        override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.id == newItem.id
        }

    }

}