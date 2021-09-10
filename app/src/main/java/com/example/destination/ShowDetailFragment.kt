package com.example.destination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.destination.model.movie.MovieResult
import com.example.destination.models.show.ShowResult
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.fragment_show_detail.*

// TODO: Rename parameter arguments, choose names that match


/**
 * A simple [Fragment] subclass.
 * Use the [ShowDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val show: ShowResult = requireArguments().get("show") as ShowResult
        tvTitleShowDetail.text = show.name
        tvOriginalTitleShowDetail.text = show.original_name
        tvRatingShowDetail.text = show.vote_average.toString()
        tvReleaseDateShowDetail.text = show.first_air_date
        tvOverviewShowDetail.text = show.overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w1280/" + show.poster_path)
            .placeholder(R.drawable.icons8_placeholder)
            .into(imageViewBigPosterShow)
    }
}