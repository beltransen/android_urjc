package tema06_pooavanzada.ej1

class Jefe(nombre: String) : Empleado(nombre) {
    fun librar() = println("El jefe $nombre está librando")
}