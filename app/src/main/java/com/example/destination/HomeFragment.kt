package com.example.destination

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.destination.model.Movie
import com.example.destination.network.API
import com.example.destination.network.RetrofitClient

import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btSend.setOnClickListener {
//            getPopularityMoviesData()
//            getLatestMoviesData()
            getTopRatedMoviesData()

            Log.d("MyLog","кнопка нажата")
//            Log.d("MyLog","good"+response.body().toString())
//            Log.d("MyLog","bad"+t.toString())

        }
    }
    fun getPopularityMoviesData(){
        val retrofit = RetrofitClient.getClient("https://api.themoviedb.org/3/").create(API::class.java)
        retrofit.getPopularityMovies("fa98e12ff4452abc0e83ab5585e62d3c")
            .enqueue(object : retrofit2.Callback<Movie> {
                override fun onResponse(
                    call: Call<Movie>,
                    response: Response<Movie> ) {
                    if(response.body()==null){
                        Log.d("MyLog","Popularity"+response.body()!!.results)
                    } else {
                        Log.d("MyLog","bad Popularity")
                    }
                }
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.d("MyLog","bad"+t.toString())
                }
            })
    }
    fun getTopRatedMoviesData(){
        val retrofit = RetrofitClient.getClient("https://api.themoviedb.org/3/").create(API::class.java)
        retrofit.getTopRatedMovies("fa98e12ff4452abc0e83ab5585e62d3c")
            .enqueue(object : retrofit2.Callback<Movie> {
                override fun onResponse(
                    call: Call<Movie>,
                    response: Response<Movie> ) {
                    Log.d("MyLog","TopRated "+response.body()!!.results)
                }
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.d("MyLog","bad"+t.toString())
                }
            })
    }
    fun getLatestMoviesData(){
        val retrofit = RetrofitClient.getClient("https://api.themoviedb.org/3/").create(API::class.java)
        retrofit.getGetLatestMovies("fa98e12ff4452abc0e83ab5585e62d3c")
            .enqueue(object : retrofit2.Callback<Movie> {
                override fun onResponse(
                    call: Call<Movie>,
                    response: Response<Movie> ) {
                    Log.d("MyLog","Latest"+response.body()!!.results)
                }
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.d("MyLog","bad"+t.toString())
                }
            })
    }
}