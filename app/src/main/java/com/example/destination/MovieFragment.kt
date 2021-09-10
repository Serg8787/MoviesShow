package com.example.destination

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.destination.adapter.MovieAdapter
import com.example.destination.adapter.OnClickMovieItem
import com.example.destination.model.movie.MovieResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movie.*


/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapterPopularityMovies: MovieAdapter
    private lateinit var adapterTopRatedMovies: MovieAdapter
    private var page = 1

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


        switchMoviews.isChecked = false
        getTopRatedMoviesData()

        switchMoviews.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                getPopularityMoviesData()
                rvTopRatedMovie.visibility = View.GONE
                rvPopularityMovie.visibility = View.VISIBLE
            } else {
                getTopRatedMoviesData()
                rvTopRatedMovie.visibility = View.VISIBLE
                rvPopularityMovie.visibility = View.GONE

            }
        }

        adapterPopularityMovies.onClickMovieItem = object : OnClickMovieItem{
            override fun itemMovieSelected(movieResult: MovieResult) {
                Toast.makeText(context, ""+ movieResult.title, Toast.LENGTH_LONG).show()
                val args = Bundle().apply {
                   putParcelable("movie",movieResult)
                }
              findNavController().navigate(R.id.action_movieFragment_to_movieDetailFragment,args)
            }
        }
        adapterTopRatedMovies.onClickMovieItem = object : OnClickMovieItem{
            override fun itemMovieSelected(movieResult: MovieResult) {
                Toast.makeText(context, ""+ movieResult.title, Toast.LENGTH_LONG).show()
                val args = Bundle().apply {
                    putParcelable("movie",movieResult)
                }
                findNavController().navigate(R.id.action_movieFragment_to_movieDetailFragment,args)
            }
        }
    }

    fun getPopularityMoviesData() {
        viewModel.loadPopulatyMovies(page)
        viewModel.listMoviePopularity.observe(viewLifecycleOwner, Observer {
            adapterPopularityMovies.updateList(it as ArrayList<MovieResult>)
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
            viewModel.listMovieTopRated.observe(viewLifecycleOwner, Observer {
                adapterTopRatedMovies.updateList(it as ArrayList<MovieResult>)
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
        adapterPopularityMovies = MovieAdapter()
        rvPopularityMovie.adapter = adapterPopularityMovies


        adapterTopRatedMovies = MovieAdapter()
        rvTopRatedMovie.adapter = adapterTopRatedMovies

    }
}


