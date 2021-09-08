package com.example.destination

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.destination.model.MovieResponse
import com.example.destination.model.MovieResult
import com.example.destination.network.API
import com.example.destination.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel() : ViewModel() {

    val listMoviePopularity = MutableLiveData<List<MovieResult>>()

    fun loadPopulatyMovies(page:Int){
        val retrofit = RetrofitClient.getClient("https://api.themoviedb.org/3/").create(API::class.java)
        retrofit.getPopularityMovies("fa98e12ff4452abc0e83ab5585e62d3c",page)
            .enqueue(object : retrofit2.Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    listMoviePopularity.postValue(response.body()!!.results as ArrayList<MovieResult>)
//                    totalPage.postValue(response.body()!!.total_pages)

                }
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("MyLog","Mistake"+t.message)
                }
            })
    }




}
