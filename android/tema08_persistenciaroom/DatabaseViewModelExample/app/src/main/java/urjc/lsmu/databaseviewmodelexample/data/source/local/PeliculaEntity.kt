package urjc.lsmu.databaseviewmodelexample.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pelicula")
data class PeliculaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val titulo: String,
    val estreno: Int,
    val director: String
)