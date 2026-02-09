package urjc.lsmu.bibliotecaddbbvm.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LibroEntity::class], version = 1)
abstract class LibrosRoomDatabase : RoomDatabase(){
    abstract fun libroDAO(): LibroDAO

    companion object{
        @Volatile
        private var INSTANCE: LibrosRoomDatabase? = null

fun getInstance(context: Context): LibrosRoomDatabase{
    val tempInstance = INSTANCE
    if(tempInstance != null){
        return tempInstance
    }
    synchronized(this){
        val instance = Room.databaseBuilder(
          context.applicationContext,
          LibrosRoomDatabase::class.java, "libros_database"
      ).allowMainThreadQueries().build()
        INSTANCE = instance
        return  instance
    }
}
    }
}