package tema04_poo.ej7

class Biblioteca(){
    val libros: MutableList<Libro> = mutableListOf()

    fun anadirLibro(libro: Libro){
        libros.add(libro)
    }

    fun prestar(libro: Libro, usuarioBiblioteca: UsuarioBiblioteca){
        if (estaDisponible(libro)){
            for (l in libros){
                if(l == libro){
                    l.disponibilidad=false
                    usuarioBiblioteca.libros.add(libro)
                }

            }
        }
    }

    fun devolver(libro: Libro, usuarioBiblioteca: UsuarioBiblioteca){
        usuarioBiblioteca.libros.remove(libro)
        for (l in libros){
            if(l == libro){
                l.disponibilidad=true
            }
        }
    }

    fun estaDisponible(libro: Libro): Boolean {
        for (l in libros){
            if(l == libro){
                return l.disponibilidad
            }
        }
        return false
    }
}