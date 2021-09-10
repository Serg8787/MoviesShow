package com.example.destination.model.actors

data class ActorResponse(
    val page: Int,
    val results: List<ActorResult>,
    val total_pages: Int,
    val total_results: Int
)