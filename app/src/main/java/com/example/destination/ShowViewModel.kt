package com.example.destination

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.destination.model.movie.MovieResponse
import com.example.destination.model.movie.MovieResult
import com.example.destination.models.show.ShowResponse
import com.example.destination.models.show.ShowResult
import com.example.destination.network.API
import com.example.destination.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowViewModel :ViewModel() {

    val listShowPopularity = MutableLiveData<List<ShowResult>>()
    val listShowTopRated = MutableLiveData<List<ShowResult>>()




    fun loadTopRatedShow(page: Int) {
        val retrofit =
            RetrofitClient.getClient("https://api.themoviedb.org/3/").create(API::class.java)
        retrofit.getTopRatedTvShows("fa98e12ff4452abc0e83ab5585e62d3c",page)
            .enqueue(object : Callback<ShowResponse>{
                override fun onResponse(
                    call: Call<ShowResponse>,
                    response: Response<ShowResponse>
                ) {
                    listShowTopRated.postValue(response.body()!!.results)
                }
                override fun onFailure(call: Call<ShowResponse>, t: Throwable) {
                    Log.d("MyLog","Mistake"+t.message)
                }
            })



    }

    fun loadPopulatityShow(page: Int) {
        val retrofit =
            RetrofitClient.getClient("https://api.themoviedb.org/3/").create(API::class.java)
        retrofit.getPopularityTvShows("fa98e12ff4452abc0e83ab5585e62d3c",page)
            .enqueue(object : Callback<ShowResponse>{
                override fun onResponse(
                    call: Call<ShowResponse>,
                    response: Response<ShowResponse>
                ) {
                    listShowPopularity.postValue(response.body()!!.results)

                }

                override fun onFailure(call: Call<ShowResponse>, t: Throwable) {
                    Log.d("MyLog","Mistake"+t.message)
                }
            })
    }
}