package tema05_herenciapolimorfismo.ej5

class Netflix() {
    val peliculas = mutableListOf<Pelicula>()
    val suscriptores = mutableListOf<Suscriptor>()

    fun anadirPelicula(pelicula: Pelicula) = peliculas.add(pelicula)
    fun anadirSuscriptor(suscriptor: Suscriptor) = suscriptores.add(suscriptor)
}