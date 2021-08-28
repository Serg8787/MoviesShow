package com.example.destination.model.id

data class AttributesX(
    val avatar_url: String,
    val average_rating: Double,
    val bounding_box: BoundingBoxX,
    val check_in_count: Int,
    val countable: Boolean,
    val destination_type: String,
    val image: Image,
    val latitude: Double,
    val long_name: String,
    val longitude: Double,
    val name: String,
    val short_name: String,
    val slug: String,
    val url: String
)