package urjc.lsmu.bibliotecaddbbvm.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "libro")
data class LibroEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val titulo: String,
    val anyo: Int,
    val portada: String,
    val autor: String,
    val resumen: String
) : Serializable