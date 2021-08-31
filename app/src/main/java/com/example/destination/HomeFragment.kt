package com.example.destination

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.destination.adapter.MovieAdapter
import com.example.destination.model.MovieResponse
import com.example.destination.model.MovieResult
import com.example.destination.network.API
import com.example.destination.network.RetrofitClient

import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    var adapter: MovieAdapter? = null
    lateinit var list: ArrayList<MovieResult>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)!!.bottomNavView.visibility = View.VISIBLE


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
            getPopularityMoviesData()
            getTopRatedMoviesData()
            getLatestMoviesData()

    }
    fun getPopularityMoviesData(){
        val retrofit = RetrofitClient.getClient("https://api.themoviedb.org/3/").create(API::class.java)
        retrofit.getPopularityMovies("fa98e12ff4452abc0e83ab5585e62d3c")
            .enqueue(object : retrofit2.Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>, response: Response<MovieResponse> ) {
                        list = response.body()!!.results as ArrayList<MovieResult>
                        adapter = MovieAdapter(context!!.applicationContext, list)
                        rvPopularityMovie.layoutManager = LinearLayoutManager(context!!.applicationContext,LinearLayoutManager.HORIZONTAL, false);
                        rvPopularityMovie.setAdapter(adapter);


                }
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("MyLog","bad"+t.toString())
                }
            })
    }
    fun getTopRatedMoviesData(){
        val retrofit = RetrofitClient.getClient("https://api.themoviedb.org/3/").create(API::class.java)
        retrofit.getTopRatedMovies("fa98e12ff4452abc0e83ab5585e62d3c")
            .enqueue(object : retrofit2.Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse> ) {
                    list = response.body()!!.results as ArrayList<MovieResult>
                    adapter = MovieAdapter(context!!.applicationContext, list)
                    rvTopRatedMovie.layoutManager = LinearLayoutManager(context!!.applicationContext,LinearLayoutManager.HORIZONTAL, false);
                    rvTopRatedMovie.setAdapter(adapter);
                }
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("MyLog","bad"+t.toString())
                }
            })
    }
    fun getLatestMoviesData(){
        val retrofit = RetrofitClient.getClient("https://api.themoviedb.org/3/").create(API::class.java)
        retrofit.getNowPlayningMovies("fa98e12ff4452abc0e83ab5585e62d3c")
            .enqueue(object : retrofit2.Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse> ) {
                    list = response.body()!!.results as ArrayList<MovieResult>
                    adapter = MovieAdapter(context!!.applicationContext, list)
                    rvLatestMovie.layoutManager = LinearLayoutManager(context!!.applicationContext,LinearLayoutManager.HORIZONTAL, false);
                    rvLatestMovie.setAdapter(adapter);
                }
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("MyLog","bad"+t.toString())
                }
            })
    }
}