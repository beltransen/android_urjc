package urjc.lsmu.ecommerce

class Producto(val nombre: String = "Vaso", val precio: Double = 2.5){
    fun obtenerInformacion(): String{
        return "Nombre: $nombre. Precio $precio"
    }
}