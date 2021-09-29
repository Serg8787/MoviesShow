package com.example.destination.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.destination.R
import com.example.destination.model.TrailerResult
import kotlinx.android.synthetic.main.trailer_item.view.*

class TrailerAdapter : RecyclerView.Adapter<ViewHolderTrailer>() {

    private var reviewList: MutableList<TrailerResult> = arrayListOf()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTrailer {
        return ViewHolderTrailer(
            LayoutInflater.from(parent.context).inflate(R.layout.trailer_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderTrailer, position: Int) {
        holder.textViewNameOfVideo.text = reviewList[position].name


    }

    override fun getItemCount(): Int {
        return reviewList.size
    }
}

class ViewHolderTrailer(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewNameOfVideo = itemView.textViewNameOfVideo
}
