package tema06_pooavanzada.ej3

fun main(){
    val thread = Thread { println("Metodo lanzado") }
    thread.start()
}