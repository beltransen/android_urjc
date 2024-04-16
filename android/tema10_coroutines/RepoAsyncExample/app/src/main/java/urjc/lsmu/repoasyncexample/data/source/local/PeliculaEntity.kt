package urjc.lsmu.repoasyncexample.data.source.local

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.launch

@Entity(tableName = "pelicula")
data class PeliculaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val titulo: String,
    val estreno: String,
    val director: String
)

