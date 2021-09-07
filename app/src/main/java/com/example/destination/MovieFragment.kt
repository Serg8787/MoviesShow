package com.example.destination

import android.os.Bundle
import android.os.RecoverySystem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        setupRecyclerView()

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

        rvPopularityMovie.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCont: Int = (recyclerView.layoutManager as LinearLayoutManager).childCount
                val pastVisibleItem:Int = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val total = adapterPopularity.itemCount

                val totalPge = 1000
                if(page < totalPge) {
                    if(visibleItemCont + pastVisibleItem >=total){
                        getPopularityMoviesData()
                        page++
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })






    }

    fun getPopularityMoviesData() {
        viewModel.loadPopulatyMovies(page)
        viewModel.listMoviePopularity.observe(viewLifecycleOwner, Observer {
            adapterPopularity.movieList = it as ArrayList<MovieResult>
        })
    }

    fun getTopRatedMoviesData() {
        viewModel.listMovieTopRated.observe(viewLifecycleOwner, Observer {
            adapterTopRated.movieList = it as ArrayList<MovieResult>
        })
    }
    fun setupRecyclerView(){
        adapterPopularity = MovieAdapter()
        rvPopularityMovie.adapter = adapterPopularity
        rvPopularityMovie.setHasFixedSize(true)


        adapterTopRated = MovieAdapter()
        rvTopRatedMovie.adapter = adapterTopRated
        rvTopRatedMovie.setHasFixedSize(true)

    }
}


