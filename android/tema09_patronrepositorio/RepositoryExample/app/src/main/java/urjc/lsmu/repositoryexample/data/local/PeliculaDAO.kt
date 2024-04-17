package urjc.lsmu.repositoryexample.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PeliculaDAO {
    @Insert
    fun insert(vararg pelicula: PeliculaEntity)

    @Update
    fun update(pelicula: PeliculaEntity)

    @Delete
    fun delete(pelicula: PeliculaEntity)

    @Query("SELECT * FROM pelicula")
    fun getAll(): LiveData<List<PeliculaEntity>>

    @Query("SELECT * FROM pelicula WHERE titulo = :nombre")
    fun getByName(nombre: String): LiveData<List<PeliculaEntity>>
}