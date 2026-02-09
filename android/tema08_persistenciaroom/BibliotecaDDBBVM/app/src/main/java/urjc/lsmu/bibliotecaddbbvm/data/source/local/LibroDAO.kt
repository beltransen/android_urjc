package urjc.lsmu.bibliotecaddbbvm.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LibroDAO {
    @Insert
    fun insert(vararg libroEntityModel: LibroEntity)

    @Update
    fun update(libroEntityModel: LibroEntity)

    @Delete
    fun delete(libroEntityModel: LibroEntity)

    @Query("SELECT * FROM libro")
    fun getAll(): LiveData<List<LibroEntity>>

    @Query("SELECT * FROM libro WHERE titulo = :nombre")
    fun getByName(nombre: String): LiveData<List<LibroEntity>>
}