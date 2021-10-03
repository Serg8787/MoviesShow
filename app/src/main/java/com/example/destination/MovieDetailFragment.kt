package com.example.destination

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.destination.adapter.OnClickMovieItem
import com.example.destination.adapter.OnClickTrailerItem
import com.example.destination.adapter.ReviewAdapter
import com.example.destination.adapter.TrailerAdapter
import com.example.destination.database.AppDatabase
import com.example.destination.model.TrailerList
import com.example.destination.model.TrailerResult
import com.example.destination.model.movie.MovieResult
import com.example.destination.model.review.ReviewResult
import com.example.destination.model.review.Reviews
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
    private lateinit var adapterTrailer: TrailerAdapter
    private lateinit var adapterReview: ReviewAdapter
    lateinit var movieDatabase: AppDatabase



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       movieDatabase = AppDatabase.getDatabase(context = context)

        val movie: MovieResult = requireArguments().get("movie") as MovieResult
        val id:Int = movie.id
        tvTitleMovieDetail.text = movie.title
        tvOriginalTitleMovieDetail.text = movie.original_title
        tvRatingMovieDetail.text = movie.vote_average.toString()
        tvReleaseDateMovieDetail.text = movie.release_date
        tvOverviewMovieDetail.text = movie.overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w1280/" + movie.poster_path)
            .placeholder(R.drawable.icons8_placeholder)
            .into(imageViewBigPosterMovie)

        var trailers = arrayListOf<TrailerResult>()
        var reviews = arrayListOf<ReviewResult>()

        val retrofit = RetrofitClient.getClient("https://api.themoviedb.org/3/").create(API::class.java)
        retrofit.getTrailerMovie(id,"fa98e12ff4452abc0e83ab5585e62d3c").enqueue(object :retrofit2.Callback<TrailerList>{
            override fun onResponse(call: Call<TrailerList>, response: Response<TrailerList>) {
                Log.d("MyLog", response.body().toString())

                 trailers = response.body()?.results as ArrayList<TrailerResult>
                if(response.body()!!.results.isEmpty()){
                    textViewLabelTrailer.visibility = View.GONE
                }

                adapterTrailer = TrailerAdapter(trailers)
                rvTrailerMovie.adapter = adapterTrailer
                adapterTrailer.onClickTrailerItem = object : OnClickTrailerItem{
                    override fun itemTrailerSelected(trailerResult: TrailerResult) {
                        Toast.makeText(context,""+trailerResult.key,Toast.LENGTH_SHORT).show()
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+trailerResult.key))
                        startActivity(intent)
                    }
                }
            }
            override fun onFailure(call: Call<TrailerList>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        retrofit.getReviewsMovie(id,"fa98e12ff4452abc0e83ab5585e62d3c").enqueue(object :retrofit2.Callback<Reviews>{
            override fun onResponse(call: Call<Reviews>, response: Response<Reviews>) {
                Log.d("MyLog", response.body().toString())
                reviews = response.body()?.results as ArrayList<ReviewResult>
                if(response.body()!!.results.isEmpty()){
                    textViewLabelReviews.visibility = View.GONE
                }
                adapterReview = ReviewAdapter(reviews)
                rvReviewsMovie.adapter = adapterReview
            }

            override fun onFailure(call: Call<Reviews>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        var favoriteMovie = movieDatabase.movieDao().getMoviesById(id)
        if(favoriteMovie==null){
            ivChangeToFavouriteMovie.setImageResource(R.drawable.icons8_heart_rose)
        } else {
            ivChangeToFavouriteMovie.setImageResource(R.drawable.icons8_heart_red)
        }

        ivChangeToFavouriteMovie.setOnClickListener{
            favoriteMovie = movieDatabase.movieDao().getMoviesById(id)
            if(favoriteMovie==null){
                ivChangeToFavouriteMovie.setImageResource(R.drawable.icons8_heart_red)
                    movieDatabase.movieDao().insertMovie(movie)
                Toast.makeText(context,"Добавлено в избранное",Toast.LENGTH_SHORT).show()
            } else {
                ivChangeToFavouriteMovie.setImageResource(R.drawable.icons8_heart_rose)
                    movieDatabase.movieDao().deleteMovie(movie)
                Toast.makeText(context,"Удалено из избранного",Toast.LENGTH_SHORT).show()

            }
        }


    }
}