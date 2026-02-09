package urjc.lsmu.listatareas.data

import java.io.Serializable

data class Tarea(
    var id: Int? = null,
    var titulo: String,
    var descripcion: String,
    var completada: Boolean = false
) : Serializable