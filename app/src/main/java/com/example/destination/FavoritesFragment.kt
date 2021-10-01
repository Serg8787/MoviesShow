package com.example.destination

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.destination.adapter.MovieAdapter
import com.example.destination.adapter.ShowAdapter
import com.example.destination.model.movie.MovieResult
import com.example.destination.models.show.ShowResult

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_favorites.*


/**
 * A simple [Fragment] subclass.
 * Use the [.newInstance] factory method to
 * create an instance of this fragment.
 */
class ActorFragment : Fragment() {
    private lateinit var viewModel:FavoritesViewModel
    private lateinit var adapterFavoritesMovies: MovieAdapter
    private lateinit var adapterFavoritesShows: ShowAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.bottomNavView.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)

        setupRecyclerView()
        getMovies()


    }
    fun setupRecyclerView(){
        adapterFavoritesMovies = MovieAdapter()
        rvFavoritesMovies.adapter = adapterFavoritesMovies


        adapterFavoritesShows= ShowAdapter()
        rvFavoritesShows.adapter = adapterFavoritesShows
    }

    fun getMovies(){
        adapterFavoritesMovies.movies = viewModel.moviesFavorites as ArrayList<MovieResult>
    }

    fun getShows(){
        adapterFavoritesShows.showsList = viewModel.showsFavorites as ArrayList<ShowResult>
    }
}