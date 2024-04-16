package com.example.retrofitexample.data

sealed class PeliculasState(
    val mensaje: String? = null,
    val datos: List<Pelicula>? = null){

    class Success(datos: List<Pelicula>) : PeliculasState(datos = datos)
    class Error(mensaje: String) : PeliculasState(mensaje = mensaje)
    class Loading : PeliculasState()
}