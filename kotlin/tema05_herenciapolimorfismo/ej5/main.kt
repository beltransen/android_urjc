package tema05_herenciapolimorfismo.ej5

fun main(){
    val netflix = Netflix()

    // Añadir suscriptores
    netflix.anadirSuscriptor(SuscriptorBasico("basico@a.com"))
    netflix.anadirSuscriptor(SuscriptorPremium("premium@a.com"))

    // Añadir peliculas
    netflix.anadirPelicula(Pelicula("AAAA", 3.5f, false))
    netflix.anadirPelicula(Pelicula("BBBB", 4.5f, true))
    netflix.anadirPelicula(Pelicula("CCCC", 1.8f, false))
    netflix.anadirPelicula(Pelicula("DDDD", 3.92f, true))

    // Mostrar catalogos
    for (sus in netflix.suscriptores){
        println(sus.email)
        sus.mostrarCatalogo(netflix.peliculas)
    }
}