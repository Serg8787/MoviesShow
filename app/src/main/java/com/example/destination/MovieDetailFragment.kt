package com.example.destination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.destination.model.movie.MovieResult
import kotlinx.android.synthetic.main.fragment_movie_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie: MovieResult = requireArguments().get("movie") as MovieResult
        tvTitleMovieDetail.text = movie.title
        tvOriginalTitleMovieDetail.text = movie.original_title
        tvRatingMovieDetail.text = movie.vote_average.toString()
        tvReleaseDateMovieDetail.text = movie.release_date
        tvOverviewMovieDetail.text = movie.overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w1280/" + movie.poster_path)
            .placeholder(R.drawable.icons8_placeholder)
            .into(imageViewBigPosterMovie)


    }


}