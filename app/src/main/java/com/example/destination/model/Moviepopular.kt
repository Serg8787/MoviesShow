package com.example.destination.model

data class Moviepopular(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)