package com.example.destination.model.id

data class Relationships(
    val continent: Continent,
    val country: Country,
    val known_for: KnownFor,
    val photos: Photos,
    val state: State,
    val travelers: Travelers
)