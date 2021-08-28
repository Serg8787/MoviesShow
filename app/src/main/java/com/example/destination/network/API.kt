package com.example.destination.network

import com.example.destination.model.country.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    // знак вопроса в конце оставляем
    @GET("auto_complete")
    fun searchID(
        @Query("q") query: String,
    ): Call<Data>
//
//
//
//    // для теста убрал знак вопроса
//    @GET("users/{login}/repos")
//    fun getListRepo(
//        @Path("login") login:String
//    ): Call<Repo>





}