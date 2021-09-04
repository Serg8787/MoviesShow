package com.example.destination

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.destination.adapter.MovieAdapter
import com.example.destination.model.MovieResponse
import com.example.destination.model.MovieResult
import com.example.destination.network.API
import com.example.destination.network.RetrofitClient
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Response

class MovieViewModel() : ViewModel() {

    val listMovie = MutableLiveData<List<MovieResult>>()
    init {
        loadPopulaty()
    }



    fun loadPopulaty(){
        val retrofit = RetrofitClient.getClient("https://api.themoviedb.org/3/").create(API::class.java)
        retrofit.getPopularityMovies("fa98e12ff4452abc0e83ab5585e62d3c")
            .enqueue(object : retrofit2.Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>, response: Response<MovieResponse>
                ) {
                    listMovie.postValue(response.body()!!.results as ArrayList<MovieResult>)

                }
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("MyLog","bad"+t.toString())
                }
            })
    }



}
