package com.example.destination.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.destination.R
import com.example.destination.model.TrailerList
import com.example.destination.model.TrailerResult
import kotlinx.android.synthetic.main.trailer_item.view.*

class TrailerAdapter(val trailerList: List<TrailerResult>) : RecyclerView.Adapter<ViewHolderTrailer>() {

//    private var trailerList: MutableList<TrailerResult> = arrayListOf()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTrailer {
        return ViewHolderTrailer(
            LayoutInflater.from(parent.context).inflate(R.layout.trailer_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderTrailer, position: Int) {
        holder.textViewNameOfVideo.text = trailerList[position].name
    }

    override fun getItemCount(): Int {
        return trailerList.size
    }
}

class ViewHolderTrailer(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewNameOfVideo = itemView.textViewNameOfVideo
}
