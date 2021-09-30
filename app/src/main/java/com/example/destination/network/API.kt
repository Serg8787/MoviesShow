package com.example.destination.network

import com.example.destination.model.TrailerList
import com.example.destination.model.TrailerResult
import com.example.destination.model.actors.ActorResponse
import com.example.destination.model.movie.MovieResponse
import com.example.destination.model.review.Reviews
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

    @GET("person/popular")
    fun getPopularityActors(@Query("api_key") apiKey: String,@Query("page") page:Int): Call<ActorResponse>

    @GET("person/latest")
    fun getLatestActors(@Query("api_key") apiKey: String,@Query("page") page:Int): Call<ActorResponse>




    @GET("movie/{movie_id}/videos")
    fun getTrailerMovie(@Path("movie_id") id:Int, @Query("api_key") apiKey: String): Call<TrailerList>

    @GET("tv/{tv_id}/videos")
    fun getTrailerShow(@Path("tv_id") id:Int, @Query("api_key") apiKey: String): Call<TrailerList>

    @GET("tv/{tv_id}/reviews")
    fun getReviewsShow(@Path("tv_id") id:Int, @Query("api_key") apiKey: String): Call<Reviews>

    @GET("movie/{movie_id}/reviews")
    fun getReviewsMovie(@Path("movie_id") id:Int, @Query("api_key") apiKey: String): Call<Reviews>





}