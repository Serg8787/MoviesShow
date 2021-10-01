package com.example.destination

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.destination.database.AppDatabase
import com.example.destination.model.movie.MovieResult
import com.example.destination.models.show.ShowResult
import androidx.lifecycle.LiveData as LiveData

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
//    lateinit var movieDatabase: AppDatabase


    var showsFavorites = listOf<ShowResult>()
    var movieDatabase = AppDatabase.getDatabase(context = application)

    var moviesFavorites = movieDatabase.movieDao().getAll()

//    fun loadFavoritesMovies(){
//       moviesFavorites = movieDatabase.movieDao().getAll()
//    }
//    fun loadFavoritesShows(){
//        showsFavorites = movieDatabase.showDao().getAll()
//    }






}