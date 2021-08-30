package com.example.destination.network

import com.example.destination.model.Moviepopular
import com.example.destination.model.Result
import retrofit2.Call
import retrofit2.http.*

interface API {

    @GET("movie/top_rated/")
    fun getPopularityMovies(@Query("api_key") apiKey: String,@Query("page") page: Int): Call<Moviepopular>
}