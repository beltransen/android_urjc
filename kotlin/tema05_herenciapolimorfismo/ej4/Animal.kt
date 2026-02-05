package tema05_herenciapolimorfismo.ej4

abstract class Animal(val nombre: String): Educacion{
    fun dormir() = println("Puedo dormir") // NO se puede sobreescribir
    abstract fun comer()
    abstract fun sonido()
    override fun saludar() {
        println("Saludo de animal")
    }
}
class Perro(name: String, val raza: String) : Animal(name){
    override fun saludar() = println("Soy ${nombre}. Toma mi patita, humano")
    override fun sentar() {
        println("Perro sentandose")
    }

    override fun comer() {
        println("Puedo comer... gatitos!")
    }
    override fun sonido() = println("Guau")
}
open class Gato(name: String, var vidas: Int) : Animal(name){
    override fun comer() {
        println("Puedo comer... ratones!")
    }
    override fun sentar() {
        println("Gato sentandose")
    }

    // final en la siguiente línea hace que clases derivadas no puedan sobreescribir más esa función
    final override fun sonido() = println("Miau")
    override fun saludar()= println("Soy ${nombre}. Acaríciame, esclavo!")
}
class GatoNegro(name: String, vidas: Int): Gato(name, vidas), Competicion{
    //override fun sonido() = println("Miau") // ERROR: No puede sobreescribir
    override fun pirueta() {
        println("Salto de alturaaa")
    }
}