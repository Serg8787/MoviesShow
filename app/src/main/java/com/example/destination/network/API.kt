package com.example.destination.network

import com.example.destination.model.Movie
import retrofit2.Call
import retrofit2.http.*

interface API {

    @GET("movie/popular")
    fun getPopularityMovies(@Query("api_key") apiKey: String): Call<Movie>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Call<Movie>


    @GET("movie/now_playing")
    fun getNowPlayningMovies(@Query("api_key") apiKey: String): Call<Movie>
}