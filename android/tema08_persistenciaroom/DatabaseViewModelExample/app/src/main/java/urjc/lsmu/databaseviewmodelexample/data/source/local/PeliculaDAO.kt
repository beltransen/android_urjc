package urjc.lsmu.databaseviewmodelexample.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PeliculaDAO {
    @Insert
    fun insert(vararg peliculaEntityModel: PeliculaEntity)

    @Update
    fun update(peliculaEntityModel: PeliculaEntity)

    @Delete
    fun delete(peliculaEntityModel: PeliculaEntity)

    @Query("SELECT * FROM pelicula")
    fun getAll(): LiveData<List<PeliculaEntity>>

    @Query("SELECT * FROM pelicula WHERE titulo = :nombre")
    fun getByName(nombre: String): LiveData<List<PeliculaEntity>>
}