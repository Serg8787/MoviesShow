package com.example.destination.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.destination.R
import com.example.destination.model.review.ReviewResult
import kotlinx.android.synthetic.main.review_item.view.*

class ReviewAdapter(val reviewList:ArrayList<ReviewResult> ) : RecyclerView.Adapter<ViewHolderReview>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderReview {
        return ViewHolderReview(
            LayoutInflater.from(parent.context).inflate(R.layout.trailer_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderReview, position: Int) {
        holder.tvContentReviewMovie.text = reviewList[position].content
        holder.tvAuthorReviewMovie.text = reviewList[position].author
    }

    override fun getItemCount(): Int {
       return reviewList.size
    }

}

class ViewHolderReview(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvAuthorReviewMovie = itemView.tvAuthorReviewMovie
    val tvContentReviewMovie = itemView.tvContentReviewMovie
    }


