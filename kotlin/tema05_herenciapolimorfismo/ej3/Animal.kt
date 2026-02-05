package tema05_herenciapolimorfismo.ej3

abstract class Animal(val nombre: String){
    abstract fun comer() // = println("Puedo comer") // NO se puede sobreescribir
    abstract fun sonido() // =println("Hago un sonido de animal") // Se puede sobreescribir
}
class Perro(name: String, val raza: String) : Animal(name){
    fun saludar() = println("Soy ${nombre}. Toma mi patita, humano")
    override fun comer() {
        println("Puedo comer... gatitos!")
    }
    override fun sonido() = println("Guau")
}
open class Gato(name: String, var vidas: Int) : Animal(name){
    override fun comer() {
        println("Puedo comer... ratones!")
    }

    // final en la siguiente línea hace que clases derivadas no puedan sobreescribir más esa función
    final override fun sonido() = println("Miau")
    fun saludar()= println("Soy ${nombre}. Acaríciame, esclavo!")
}
class GatoNegro(name: String, vidas: Int) : Gato(name, vidas){
    //override fun sonido() = println("Miau") // ERROR: No puede sobreescribir
}