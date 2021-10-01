package com.example.destination

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.destination.database.AppDatabase

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {


    var movieDatabase = AppDatabase.getDatabase(context = application)

    var moviesFavorites = movieDatabase.movieDao().getAll()
    var showsFavorites = movieDatabase.showDao().getAll()


}