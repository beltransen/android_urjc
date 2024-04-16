package urjc.lsmu.repoasyncexample.data

import java.time.LocalDate

data class Pelicula(
    val id: Int?,
    val titulo: String,
    val estreno: LocalDate,
    val director: String
)