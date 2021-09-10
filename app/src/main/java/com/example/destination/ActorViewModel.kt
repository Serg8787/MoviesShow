package com.example.destination

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.destination.model.actors.ActorResponse
import com.example.destination.model.actors.ActorResult
import com.example.destination.model.movie.MovieResponse
import com.example.destination.model.movie.MovieResult
import com.example.destination.network.API
import com.example.destination.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActorViewModel() : ViewModel() {

    val listActorPopularity = MutableLiveData<List<ActorResult>>()
    val listActorLatest = MutableLiveData<List<ActorResult>>()

    fun loadPopulatyActor(page:Int){
        val retrofit = RetrofitClient.getClient("https://api.themoviedb.org/3/").create(API::class.java)
        retrofit.getPopularityActors("fa98e12ff4452abc0e83ab5585e62d3c",page)
            .enqueue(object : retrofit2.Callback<ActorResponse> {
                override fun onResponse(
                    call: Call<ActorResponse>,
                    response: Response<ActorResponse>
                ) {
                    listActorPopularity.postValue(response.body()!!.results)
                }

                override fun onFailure(call: Call<ActorResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
    fun loadLatestActor(page:Int){
        val retrofit = RetrofitClient.getClient("https://api.themoviedb.org/3/").create(API::class.java)
        retrofit.getLatestActors("fa98e12ff4452abc0e83ab5585e62d3c",page)
            .enqueue(object : retrofit2.Callback<ActorResponse> {
                override fun onResponse(
                    call: Call<ActorResponse>,
                    response: Response<ActorResponse>
                ) {
                    listActorLatest.postValue(response.body()!!.results)
                }

                override fun onFailure(call: Call<ActorResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }





}