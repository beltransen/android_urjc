package com.example.retrofitexample.data

import com.example.retrofitexample.data.remote.PeliculaDto
import java.time.LocalDate

fun PeliculaDto.toDomain(): Pelicula {
    return Pelicula(
        id = this.id,
        titulo = this.title,
        descripcion = this.overview,
        estreno = this.release_date?.let {LocalDate.parse(it)},
        puntuacion = this.vote_average,
        posterURL = this.poster_path
    )
}