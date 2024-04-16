package com.example.retrofitexample.data

import java.time.LocalDate

data class Pelicula(
    val id: Int?,
    val titulo: String?,
    val descripcion: String?,
    val estreno: LocalDate?,
    val puntuacion: Double?,
    val posterURL: String?
)