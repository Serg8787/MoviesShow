package com.example.destination.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.destination.R
import com.example.destination.models.show.ShowResult
import kotlinx.android.synthetic.main.show_item.view.*

class ShowAdapter : RecyclerView.Adapter<ViewHolderShow>() {

    var showsList: ArrayList<ShowResult> = arrayListOf<ShowResult>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onClickShowItem: OnClickShowItem? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderShow {
        return ViewHolderShow(
            LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderShow, position: Int) {
        holder.nameShow.text = showsList[position].name
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w1280/" + showsList[position].backdrop_path)
            .placeholder(R.drawable.icons8_placeholder)
            .into(holder.posterShow)
        holder.voteAverageShow.text = showsList[position].vote_average.toString()
        holder.itemView.setOnClickListener {

            onClickShowItem?.itemShowSelected(showsList[position])
        }


    }

    override fun getItemCount(): Int {
        return showsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(itemsShows: ArrayList<ShowResult>) {
        showsList.addAll(itemsShows)
        notifyItemRangeInserted(
            this.showsList.size,
            showsList.size - 1 ) }
}
class ViewHolderShow(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameShow = itemView.tvTitleShowItem
    val posterShow = itemView.ivPosterShow
    val voteAverageShow = itemView.ivVoteAverageShow

}
interface OnClickShowItem {
    fun itemShowSelected(showResult: ShowResult)
}