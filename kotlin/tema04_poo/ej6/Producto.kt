package tema04_poo.ej6

class Producto(val nombre: String = "Vaso", val precio: Double = 2.5){
    fun obtenerInformacion(): String{
        return "Nombre: $nombre. Precio $precio"
    }

    operator fun component1() = nombre
    operator fun component2() = precio
}