package urjc.lsmu.databaseexample.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PeliculaEntity::class], version = 1)
abstract class PeliculasRoomDatabase : RoomDatabase(){
    abstract fun peliculaDAO(): PeliculaDAO

    companion object{
        @Volatile
        private var INSTANCE: PeliculasRoomDatabase? = null

        fun getInstance(context: Context): PeliculasRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                  context.applicationContext,
                  PeliculasRoomDatabase::class.java, "pelicula_database"
              ).allowMainThreadQueries().build()
                INSTANCE = instance
                return  instance
            }
        }
    }
}