package com.example.destination.network

import com.example.destination.model.movie.MovieResponse
import com.example.destination.models.show.ShowResponse
import retrofit2.Call
import retrofit2.http.*

interface API {

    @GET("movie/popular")
    fun getPopularityMovies(@Query("api_key") apiKey: String,@Query("page") page:Int): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String,@Query("page") page:Int): Call<MovieResponse>




    @GET("tv/top_rated")
    fun getTopRatedTvShows(@Query("api_key") apiKey: String,@Query("page") page:Int): Call<ShowResponse>


    @GET("tv/popular")
    fun getPopularityTvShows(@Query("api_key") apiKey: String,@Query("page") page:Int): Call<ShowResponse>

}