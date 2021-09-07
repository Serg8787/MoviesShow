package com.example.destination.model

data class MovieResponse(
    var page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)