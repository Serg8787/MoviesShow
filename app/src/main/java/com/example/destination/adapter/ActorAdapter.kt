package com.example.destination.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.destination.R
import com.example.destination.model.actors.ActorResult
import kotlinx.android.synthetic.main.actor_item.view.*

class ActorAdapter : RecyclerView.Adapter<ViewHolderActor>() {


    private var actors: MutableList<ActorResult> = arrayListOf()
    var onClickActorItem: OnClickActorItem? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderActor {
        return ViewHolderActor(
            LayoutInflater.from(parent.context).inflate(R.layout.actor_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderActor, position: Int) {

        holder.nameActor.text = actors[position].name
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w1280/" + actors[position].profile_path)
            .placeholder(R.drawable.icons8_placeholder)
            .into(holder.posterShow)
        holder.voteAverageShow.text = actors[position].popularity.toString()
        holder.itemView.setOnClickListener {
            onClickActorItem?.itemActorSelected(actors[position])
        }
    }
    override fun getItemCount(): Int {
        return actors.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(itemsActors: ArrayList<ActorResult>) {
        actors.addAll(itemsActors)
        notifyItemRangeInserted(
            this.actors.size,
            actors.size - 1 ) }
}
class ViewHolderActor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameActor = itemView.tvTitleActorItem
    val posterShow = itemView.ivPosterActor
    val voteAverageShow = itemView.ivVoteAverageActor

}
interface OnClickActorItem {
    fun itemActorSelected(actorResult: ActorResult)
}