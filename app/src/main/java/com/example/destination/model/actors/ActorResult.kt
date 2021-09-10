package com.example.destination.model.actors

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ActorResult(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
//    val known_for: List<KnownForActor>,
    val known_for_department: String,
    val name: String,
    val popularity: Double,
    val profile_path: String
):Parcelable