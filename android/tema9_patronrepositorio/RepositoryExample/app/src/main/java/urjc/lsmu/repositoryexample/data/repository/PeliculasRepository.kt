package urjc.lsmu.repositoryexample.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import urjc.lsmu.repositoryexample.data.Pelicula
import urjc.lsmu.repositoryexample.data.source.local.PeliculaEntity
import urjc.lsmu.repositoryexample.data.source.local.PeliculaDAO
import urjc.lsmu.repositoryexample.data.toDomain
import urjc.lsmu.repositoryexample.data.toEntity

class PeliculasRepository(private val dao: PeliculaDAO) {
    fun getAllPeliculas(): LiveData<List<Pelicula>> = dao.getAll().map { listaPeliculas ->
        listaPeliculas.map { it.toDomain() } }

    fun addPelicula(pelicula: Pelicula){
        dao.insert(pelicula.toEntity())
    }
    fun delPelicula(pelicula: Pelicula){
        dao.delete(pelicula.toEntity())
    }
}


// Codigo para diapos
class PeliculasAPI

class PeliculasRepository2(
    private val dao: PeliculaDAO,
    private val api: PeliculasAPI
) {
    var peliculas: LiveData<List<PeliculaEntity>> = dao.getAll()
    fun getAllPeliculas(): LiveData<List<PeliculaEntity>>{
      if (peliculas.value?.isEmpty() == true) {
          var remotePeliculas = getPeliculasRemote()
          // Insertar películas remotas en bbdd local
          println(remotePeliculas)
      }
        return peliculas
    }

    // Función para los datos de películas de un servidor remoto con la API Rest
    fun getPeliculasRemote(): List<PeliculaEntity> = TODO()

    fun addPelicula(pelicula: PeliculaEntity){
        dao.insert(pelicula)
    }
    fun delPelicula(pelicula: PeliculaEntity){
        dao.delete(pelicula)
    }

}