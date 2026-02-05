package tema04_poo.ej7

class UsuarioBiblioteca(val nombre: String, val id: String){
    val libros: MutableList<Libro> = mutableListOf()
}