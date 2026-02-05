package tema04_poo.ej7

fun main(){
    val l1 = Libro("AAAA", "aaaa")
    val l2 = Libro("BBBB", "bbbb")
    val l3 = Libro("CCCC", "cccc")

    val miBiblioteca = Biblioteca()
    miBiblioteca.anadirLibro(l1)
    miBiblioteca.anadirLibro(l2)
    miBiblioteca.anadirLibro(l3)

    val us1 = UsuarioBiblioteca("Jorge", "3213241234124")
    miBiblioteca.prestar(l2, us1)
    println("Libros en la biblioteca después de prestar:")

    for(l in miBiblioteca.libros){
        println(l)
    }
    println("\nLibros prestados al usuario:")
    for(l in us1.libros){
        println(l)
    }

    miBiblioteca.devolver(l2, us1)
    println("\nLibros en la biblioteca después de devolver:")
    for(l in miBiblioteca.libros){
        println(l)
    }
}