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
import androidx.recyclerview.widget.RecyclerView
import com.example.destination.adapter.MovieAdapter
import com.example.destination.model.MovieResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movie.*
import com.google.android.material.bottomnavigation.BottomNavigationView


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
    private lateinit var bottomNavView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        (activity as AppCompatActivity?)!!.bottomNavView.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        setupRecyclerView()

        getPopularityMoviesData()
        switchMoviews.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                getTopRatedMoviesData()
            rvTopRatedMovie.visibility = View.VISIBLE
            rvPopularityMovie.visibility = View.GONE
            } else {
                getPopularityMoviesData()
            rvTopRatedMovie.visibility = View.GONE
            rvPopularityMovie.visibility = View.VISIBLE
            }
        }


    }

    fun getPopularityMoviesData() {
        viewModel.loadPopulatyMovies(page)
        viewModel.listMoviePopularity.observe(viewLifecycleOwner, Observer {
            adapterPopularity.updateList(it as ArrayList<MovieResult>)
        })

        rvPopularityMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =
                    (rvPopularityMovie.layoutManager as LinearLayoutManager).itemCount
                val visibleItemCount: Int =
                    (rvPopularityMovie.layoutManager as LinearLayoutManager).childCount
                val firstVisibleItem: Int =
                    (rvPopularityMovie.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    rvPopularityMovie.removeOnScrollListener(this)
                    page++
                    getPopularityMoviesData()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

        fun getTopRatedMoviesData() {
            viewModel.loadTopRatedMovies(page)
            viewModel.listTopRated.observe(viewLifecycleOwner, Observer {
                adapterTopRated.updateList(it as ArrayList<MovieResult>)
            })

            rvTopRatedMovie.addOnScrollListener(object :RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val totalItemCount = (rvTopRatedMovie.layoutManager as LinearLayoutManager).itemCount
                    val visibleItemCount: Int = (rvTopRatedMovie.layoutManager as LinearLayoutManager).childCount
                    val firstVisibleItem:Int = (rvTopRatedMovie.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                    if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                        rvTopRatedMovie.removeOnScrollListener(this)
                        page++
                        getTopRatedMoviesData()
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
    }


    fun setupRecyclerView(){
        adapterPopularity = MovieAdapter()
        rvPopularityMovie.adapter = adapterPopularity


        adapterTopRated = MovieAdapter()
        rvTopRatedMovie.adapter = adapterTopRated

    }
}

