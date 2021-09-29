package com.example.destination

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.destination.adapter.ReviewAdapter
import com.example.destination.model.TrailerList
import com.example.destination.model.TrailerResult
import com.example.destination.model.movie.MovieResult
import com.example.destination.network.API
import com.example.destination.network.RetrofitClient
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import retrofit2.Call
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailFragment : Fragment() {
    private lateinit var adapterReview: ReviewAdapter


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
        val id:Int = movie.id
        Toast.makeText(context,""+id,Toast.LENGTH_LONG).show()
        tvTitleMovieDetail.text = movie.title
        tvOriginalTitleMovieDetail.text = movie.original_title
        tvRatingMovieDetail.text = movie.vote_average.toString()
        tvReleaseDateMovieDetail.text = movie.release_date
        tvOverviewMovieDetail.text = movie.overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w1280/" + movie.poster_path)
            .placeholder(R.drawable.icons8_placeholder)
            .into(imageViewBigPosterMovie)


        val retrofit = RetrofitClient.getClient("https://api.themoviedb.org/3/").create(API::class.java)
        retrofit.getTrailer(id,"fa98e12ff4452abc0e83ab5585e62d3c").enqueue(object :retrofit2.Callback<TrailerList>{
            override fun onResponse(call: Call<TrailerList>, response: Response<TrailerList>) {
                Log.d("MyLog", response.body().toString())
            }

            override fun onFailure(call: Call<TrailerList>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })



    }

}