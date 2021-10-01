package com.example.destination.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.destination.model.movie.MovieResult
@Dao
interface MovieDAO {

    @Query("SELECT * FROM movies")
    fun getAll(): List<MovieResult>

    @Insert
    fun insertMovie(movieResult: MovieResult)

    @Delete
    fun deleteMovie(movieResult: MovieResult)

    @Update
    fun updateMovie(movieResult: MovieResult)

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMoviesById(id: Int): MovieResult?

}