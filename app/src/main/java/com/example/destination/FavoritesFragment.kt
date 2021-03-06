package com.example.destination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.destination.adapter.MovieAdapter
import com.example.destination.adapter.OnClickMovieItem
import com.example.destination.adapter.OnClickShowItem
import com.example.destination.adapter.ShowAdapter
import com.example.destination.model.movie.MovieResult
import com.example.destination.models.show.ShowResult

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_show.*


/**
 * A simple [Fragment] subclass.
 * Use the [.newInstance] factory method to
 * create an instance of this fragment.
 */
class ActorFragment : Fragment() {
    private lateinit var viewModel:FavoritesViewModel
    private lateinit var adapterFavoritesMovies: MovieAdapter
    private lateinit var adapterFavoritesShows: ShowAdapter


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
        tvMoviesFavorites.setTextColor(resources.getColor(R.color.teal_200))

        swFavorites.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                getShows()
                rvFavoritesMovies.visibility = View.INVISIBLE
                rvFavoritesShows.visibility = View.VISIBLE
                tvMoviesFavorites.setTextColor(resources.getColor(android.R.color.holo_orange_light))
                tvFavoritesShow.setTextColor(resources.getColor(R.color.teal_200))
            } else {
                getMovies()
                rvFavoritesMovies.visibility = View.VISIBLE
                rvFavoritesShows.visibility = View.INVISIBLE
                tvFavoritesShow.setTextColor(resources.getColor(android.R.color.holo_orange_light))
                tvMoviesFavorites.setTextColor(resources.getColor(R.color.teal_200))
            }
        }

        adapterFavoritesMovies.onClickMovieItem = object : OnClickMovieItem {
            override fun itemMovieSelected(movieResult: MovieResult) {
                val args = Bundle().apply {
                    putParcelable("movie",movieResult)
                }
                findNavController().navigate(R.id.action_FavoritesFragment_to_movieDetailFragment,args)
            }
        }
        adapterFavoritesShows.onClickShowItem = object : OnClickShowItem {
            override fun itemShowSelected(showResult: ShowResult) {
                val args = Bundle().apply {
                    putParcelable("show",showResult)
                }
                findNavController().navigate(R.id.action_FavoritesFragment_to_showDetailFragment,args)
            }

        }


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