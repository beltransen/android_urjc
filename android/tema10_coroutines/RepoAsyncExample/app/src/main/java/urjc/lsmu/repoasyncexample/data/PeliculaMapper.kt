package urjc.lsmu.repoasyncexample.data

import urjc.lsmu.repoasyncexample.data.source.local.PeliculaEntity
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