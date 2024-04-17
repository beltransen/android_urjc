package urjc.lsmu.repositoryexample.data

import urjc.lsmu.repositoryexample.data.local.PeliculaEntity
import java.time.LocalDate

fun PeliculaEntity.toDomain(): Pelicula {
    return Pelicula(
        id = this.id,
        titulo = this.titulo,
        estreno = LocalDate.parse(this.estreno),
        director = this.director
    )
}

fun Pelicula.toEntity(): PeliculaEntity {
    return PeliculaEntity(
        id = this.id,
        titulo = this.titulo,
        estreno = this.estreno.toString(),
        director = this.director
    )
}