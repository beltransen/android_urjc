package urjc.lsmu.databaseexample.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PeliculaDAO {
    @Insert
    fun insert(vararg peliculaModel: Pelicula)

    @Update
    fun update(peliculaModel: Pelicula)

    @Delete
    fun delete(peliculaModel: Pelicula)

    @Query("SELECT * FROM pelicula_table")
    fun getAll(): LiveData<List<Pelicula>>

    @Query("SELECT * FROM pelicula_table WHERE pelicula_titulo = :nombre")
    fun getByName(nombre: String): LiveData<List<Pelicula>>
}