package com.example.destination.network

import com.example.destination.model.Moviepopular
import com.example.destination.model.Result
import retrofit2.Call
import retrofit2.http.*

interface API {

    @GET("movie/popular")
    fun getPopularityMovies(@Query("api_key") apiKey: String): Call<Moviepopular>
}