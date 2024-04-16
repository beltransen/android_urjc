package urjc.lsmu.repoasyncexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import urjc.lsmu.repoasyncexample.data.Pelicula
import urjc.lsmu.repoasyncexample.viewmodels.PeliculasViewModelFactory
import urjc.lsmu.repoasyncexample.databinding.ActivityMainBinding
import urjc.lsmu.repoasyncexample.viewmodels.PeliculasViewModel
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: PeliculasViewModel // Declaramos el viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Instanciamos el ViewModel con el ViewModelProvider a través del Factory creado para poder pasarle parámetros
        viewModel = ViewModelProvider(this, PeliculasViewModelFactory(applicationContext))[PeliculasViewModel::class.java]

        binding.btnAddPelicula.setOnClickListener {
            val peli = Pelicula(null, "Todo a la vez en todas partes",
                LocalDate.parse("2022-03-11"), "Dan Kwan, Daniel Scheinert")
            viewModel.addPelicula(peli) // Añadimos la película usando la función del viewmodel
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val miAdaptador = AdaptadorPeliculas()
        binding.recyclerView.adapter = miAdaptador

        val peliculas_observer = Observer<List<Pelicula>> {
            miAdaptador.setPeliculas(it)
        }

        lifecycleScope.launch{
            coroutineFunction()
        }
        lifecycleScope.launch(Dispatchers.IO){
            coroutineFunction2()
        }

        Log.d("Main", "onCreate")
        viewModel.peliculas.observe(this, peliculas_observer) // Observamos la lista de películas del viewmodel
    }

    suspend fun coroutineFunction(){
        delay(1000L)
        Log.d("Main", "coroutine")
    }

    suspend fun coroutineFunction2(){
        withContext(Dispatchers.IO){
//            delay(500L)
            Log.d("Main", "context")
        }
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("Main", "context")
        }
    }
}
//
//class Libro
//
//@Dao
//interface LibrosDao {
//
//    @Query("SELECT * FROM libros")
//    suspend fun getAll(): Array<Libro>
//
//    @Insert
//    suspend fun insert(vararg libro: Libro)
//
//    @Update
//    suspend fun update(libro: Libro)
//
//    @Delete
//    suspend fun delete(libro: Libro)
//}
//
