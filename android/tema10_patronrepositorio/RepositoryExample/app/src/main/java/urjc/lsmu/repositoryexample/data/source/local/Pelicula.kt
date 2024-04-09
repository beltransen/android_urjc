package urjc.lsmu.repositoryexample.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pelicula_table")
data class Pelicula(
    @PrimaryKey(autoGenerate = true)
    val peliculaId: Int?,
    @ColumnInfo(name = "pelicula_titulo")
    val titulo: String,
    @ColumnInfo(name = "pelicula_anyo")
    val anyo: Int,
    val director: String
)