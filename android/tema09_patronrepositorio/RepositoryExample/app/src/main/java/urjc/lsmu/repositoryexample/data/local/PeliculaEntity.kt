package urjc.lsmu.repositoryexample.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pelicula")
data class PeliculaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val titulo: String,
    val estreno: String,
    val director: String
)