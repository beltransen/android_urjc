package com.example.retrofitexample.data.repositories

import com.example.retrofitexample.data.PeliculasState
import com.example.retrofitexample.data.remote.RetroFitInstance
import com.example.retrofitexample.data.remote.TMDBResponse
import com.example.retrofitexample.data.toDomain
import retrofit2.Response

class PeliculasRepository {
    suspend fun getAllPeliculas(): PeliculasState {
        val response = RetroFitInstance.api.getAllPeliculas()
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return PeliculasState.Success(resultResponse.results.map { it.toDomain() })
            }
        }
        return PeliculasState.Error(response.message())
    }
}