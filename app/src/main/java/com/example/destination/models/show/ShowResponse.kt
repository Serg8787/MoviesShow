package com.example.destination.models.show

data class ShowResponse(
    val page: Int,
    val results: List<ShowResult>,
    val total_pages: Int,
    val total_results: Int
)