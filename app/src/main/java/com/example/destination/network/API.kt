package com.example.destination.network

import com.example.destination.model.Moviepopular
import com.example.destination.model.Result
import retrofit2.Call
import retrofit2.http.*

interface API {

    @GET("movie/popular")
    fun getPopularityMovies(@Query("api_key") apiKey: String): Call<Moviepopular>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Call<Moviepopular>


    @GET("movie/latest/")
    fun getGetLatestMovies(@Query("api_key") apiKey: String): Call<Moviepopular>
}