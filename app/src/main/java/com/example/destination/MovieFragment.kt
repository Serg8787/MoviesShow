package com.example.destination

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.destination.adapter.MovieAdapter
import com.example.destination.model.MovieResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movie.*


/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {
    var adapter: MovieAdapter? = null
    var list: ArrayList<MovieResult>? = null
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)!!.bottomNavView.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPopularityMoviesData()
            getTopRatedMoviesData()
//            getLatestMoviesData()

    }

    fun getPopularityMoviesData() {
        val adapter = MovieAdapter()
        rvPopularityMovie.adapter = adapter

        viewModel.listMoviePopularity.observe(viewLifecycleOwner, Observer {
            Log.i("MyLog", "" + it)
            rvPopularityMovie.layoutManager = LinearLayoutManager(
                MainActivity(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter.movieList = it as ArrayList<MovieResult>
        })
    }
    fun getTopRatedMoviesData() {
        val adapter = MovieAdapter()

        rvTopRatedMovie.adapter = adapter
        viewModel.listMovieTopRated.observe(viewLifecycleOwner, Observer {
            Log.i("MyLog", "" + it)
            rvTopRatedMovie.layoutManager = LinearLayoutManager(
                MainActivity(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter.movieList = it as ArrayList<MovieResult>
        })
    }
}

//
//    fun getLatestMoviesData() {
//        val retrofit =
//            RetrofitClient.getClient("https://api.themoviedb.org/3/").create(API::class.java)
//        retrofit.getNowPlayningMovies("fa98e12ff4452abc0e83ab5585e62d3c")
//            .enqueue(object : retrofit2.Callback<MovieResponse> {
//                override fun onResponse(
//                    call: Call<MovieResponse>,
//                    response: Response<MovieResponse>
//                ) {
//                    list = response.body()!!.results as ArrayList<MovieResult>
//                    adapter = MovieAdapter(context!!.applicationContext, list)
//                    rvLatestMovie.layoutManager = LinearLayoutManager(
//                        context!!.applicationContext,
//                        LinearLayoutManager.HORIZONTAL,
//                        false
//                    );
//                    rvLatestMovie.setAdapter(adapter);
//                }
//
//                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                    Log.d("MyLog", "bad" + t.toString())
//                }
//            })

