package com.example.retrofitexample.data.remote

data class TMDBResponse(
    val page: Int,
    val results: List<PeliculaDto>,
    val totalPages: Int,
    val totalResults: Int
)