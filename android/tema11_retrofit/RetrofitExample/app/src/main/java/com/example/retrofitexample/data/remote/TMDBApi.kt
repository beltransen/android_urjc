package com.example.retrofitexample.data.remote

import com.example.retrofitexample.data.remote.TMDBConstants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {

    @GET("3/movie/popular")
    suspend fun getAllPeliculas(
        @Query("api_key")
        key: String = API_KEY
    ): Response<TMDBResponse>

}