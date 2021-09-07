package com.example.destination.network

import com.example.destination.model.MovieResponse
import com.example.destination.model.MovieResult
import retrofit2.Call
import retrofit2.http.*

interface API {

    @GET("movie/popular")
    fun getPopularityMovies(@Query("api_key") apiKey: String,@Query("page") page:Int): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String,@Query("page") page:Int): Call<MovieResponse>


}