package com.example.destination.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.destination.model.movie.MovieResult
import com.example.destination.models.show.ShowResult

@Database(entities = [MovieResult::class, ShowResult::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDAO
    abstract fun showDao(): ShowDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context?): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context!!.applicationContext,
                    AppDatabase::class.java,
                    "video_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}