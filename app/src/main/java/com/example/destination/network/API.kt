package com.example.destination.network

import com.example.destination.model.Show
import retrofit2.Call
import retrofit2.http.*

interface API {

    @GET("shows?")
    fun search(@Query("q") query: String): Call<Show>}