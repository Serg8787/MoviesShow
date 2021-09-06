package com.example.destination

import android.os.Bundle
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
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapterPopularity: MovieAdapter
    private lateinit var adapterTopRated: MovieAdapter
    private var page = 1

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

        adapterPopularity = MovieAdapter()
        adapterTopRated = MovieAdapter()
        rvPopularityMovie.adapter = adapterPopularity
        rvTopRatedMovie.adapter = adapterTopRated

        getPopularityMoviesData()



        switchMoviews.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                getTopRatedMoviesData()
                rvTopRatedMovie.visibility = View.VISIBLE
                rvPopularityMovie.visibility = View.GONE
            }else{
                getPopularityMoviesData()
                rvTopRatedMovie.visibility = View.GONE
                rvPopularityMovie.visibility = View.VISIBLE
            }
        }
    }

    fun getPopularityMoviesData() {
        viewModel.loadPopulatyMovies(page)
        viewModel.listMoviePopularity.observe(viewLifecycleOwner, Observer {
            rvPopularityMovie.layoutManager = LinearLayoutManager(
                MainActivity(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapterPopularity.movieList = it as ArrayList<MovieResult>
        })
    }

    fun getTopRatedMoviesData() {
        viewModel.listMovieTopRated.observe(viewLifecycleOwner, Observer {
            rvTopRatedMovie.layoutManager = LinearLayoutManager(
                MainActivity(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapterTopRated.movieList = it as ArrayList<MovieResult>
        })
    }
}


