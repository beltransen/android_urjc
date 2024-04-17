package com.example.retrofitexample.data.repositories

import com.example.retrofitexample.data.PeliculasState
import com.example.retrofitexample.data.remote.RetroFitInstance
import com.example.retrofitexample.data.remote.TMDBResponse
import com.example.retrofitexample.data.toDomain
import retrofit2.Response
import java.io.IOException

class PeliculasRepository {
    suspend fun getAllPeliculas(): PeliculasState {
        try {
            val response = RetroFitInstance.api.getAllPeliculas()
            if(response.isSuccessful) {
                response.body()?.let { resultResponse ->
                    return PeliculasState.Success(resultResponse.results.map { it.toDomain() })
                }
            }
            return PeliculasState.Error(response.message())
        }catch (e: IOException){
            return PeliculasState.Error("Fallo de conexión")
        }
    }
}