package tema06_pooavanzada.ej1

sealed class Empleado(val nombre: String) {
    fun trabajar(){
        println("El trabajador $nombre está trabajando")
    }
}

