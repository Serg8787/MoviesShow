package com.example.destination.model.country

data class Attributes(
    val average_rating: Double,
    val bounding_box: BoundingBox,
    val check_in_count: Int,
    val countable: Boolean,
    val destination_type: String,
    val latitude: Double,
    val long_name: String,
    val longitude: Double,
    val name: String,
    val short_name: String,
    val slug: String
)