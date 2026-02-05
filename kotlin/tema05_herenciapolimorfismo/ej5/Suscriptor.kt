package tema05_herenciapolimorfismo.ej5

abstract class Suscriptor(val email: String, val precio: Float) {
    abstract fun mostrarCatalogo(catalogo: List<Pelicula>)
}

class SuscriptorBasico(email: String) : Suscriptor(email, 7.99f) {
    override fun mostrarCatalogo(catalogo: List<Pelicula>) {
        for (p in catalogo){
            if (!p.premium){
                println(p)
            }
        }
    }
}

class SuscriptorPremium(email: String) : Suscriptor(email, 14.99f) {
    override fun mostrarCatalogo(catalogo: List<Pelicula>) {
        for (p in catalogo){
            println(p)
        }
    }
}