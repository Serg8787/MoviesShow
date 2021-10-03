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
import com.bumptech.glide.Glide
import com.example.destination.adapter.OnClickTrailerItem
import com.example.destination.adapter.ReviewAdapter
import com.example.destination.adapter.TrailerAdapter
import com.example.destination.database.AppDatabase
import com.example.destination.model.TrailerList
import com.example.destination.model.TrailerResult
import com.example.destination.model.review.ReviewResult
import com.example.destination.model.review.Reviews
import com.example.destination.models.show.ShowResult
import com.example.destination.network.API
import com.example.destination.network.RetrofitClient
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.fragment_show_detail.*
import retrofit2.Call
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match


/**
 * A simple [Fragment] subclass.
 * Use the [ShowDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowDetailFragment : Fragment() {
    private lateinit var adapterTrailer: TrailerAdapter
    private lateinit var adapterReview: ReviewAdapter
    lateinit var movieDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDatabase = AppDatabase.getDatabase(context = context)

        val show: ShowResult = requireArguments().get("show") as ShowResult
        val id = show.id
        Log.d("MyLog",id.toString())
        tvTitleShowDetail.text = show.name
        tvOriginalTitleShowDetail.text = show.original_name
        tvRatingShowDetail.text = show.vote_average.toString()
        tvReleaseDateShowDetail.text = show.first_air_date
        tvOverviewShowDetail.text = show.overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w1280/" + show.poster_path)
            .placeholder(R.drawable.icons8_placeholder)
            .into(imageViewBigPosterShow)

        var trailers = arrayListOf<TrailerResult>()
        var reviews = arrayListOf<ReviewResult>()
//


        val retrofit = RetrofitClient.getClient("https://api.themoviedb.org/3/").create(API::class.java)
        retrofit.getTrailerShow(id,"fa98e12ff4452abc0e83ab5585e62d3c").enqueue(object : retrofit2.Callback<TrailerList>{
            override fun onResponse(call: Call<TrailerList>, response: Response<TrailerList>) {
                trailers = response.body()?.results as ArrayList<TrailerResult>
                if(response.body()!!.results.isEmpty()){
                    textViewLabelTrailerShow.visibility = View.GONE
                }
                adapterTrailer = TrailerAdapter(trailers)
                rvTrailerShow.adapter = adapterTrailer
                adapterTrailer.onClickTrailerItem = object : OnClickTrailerItem {
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

        retrofit.getReviewsShow(id,"fa98e12ff4452abc0e83ab5585e62d3c").enqueue(object :retrofit2.Callback<Reviews>{
            override fun onResponse(call: Call<Reviews>, response: Response<Reviews>) {
                Log.d("MyLog", response.body().toString())
                if(response.body()!!.results.isEmpty()){
                    textViewLabelReviewsShow.visibility = View.GONE
                }
                reviews = response.body()?.results as ArrayList<ReviewResult>
                adapterReview = ReviewAdapter(reviews)
                rvReviewsShow.adapter = adapterReview
            }

            override fun onFailure(call: Call<Reviews>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        var favoriteShow = movieDatabase.showDao().getShowById(id)
        if(favoriteShow==null){
            ivChangeToFavouriteShow.setImageResource(R.drawable.icons8_heart_rose)
        } else {
            ivChangeToFavouriteShow.setImageResource(R.drawable.icons8_heart_red)
        }

        ivChangeToFavouriteShow.setOnClickListener{
            favoriteShow = movieDatabase.showDao().getShowById(id)
            if(favoriteShow==null){
                ivChangeToFavouriteShow.setImageResource(R.drawable.icons8_heart_red)
                movieDatabase.showDao().insertShow(show)
                Toast.makeText(context,"Добавлено в избранное", Toast.LENGTH_SHORT).show()
            } else {
                ivChangeToFavouriteShow.setImageResource(R.drawable.icons8_heart_rose)
                movieDatabase.showDao().deleteShow(show)
                Toast.makeText(context,"Удалено из избранного", Toast.LENGTH_SHORT).show()

            }
        }

    }
}