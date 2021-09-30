package com.example.destination.models.show

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "shows")
data class ShowResult(
    val backdrop_path: String,
    val first_air_date: String,
    @PrimaryKey
    val id: Int,
    val name: String?,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
) :Parcelable