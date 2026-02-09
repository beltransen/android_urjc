package urjc.lsmu.listatareas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import urjc.lsmu.listatareas.data.Tarea
import urjc.lsmu.listatareas.data.TareasViewModel
import urjc.lsmu.listatareas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var TASK_COUNT = 0
    val viewModel: TareasViewModel by viewModels()

    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            val tarea = it.data!!.getSerializableExtra("tarea") as Tarea
            if (tarea.id == null){
                tarea.id = TASK_COUNT
                viewModel.addTarea(tarea)
                TASK_COUNT++
            }else{
                viewModel.editTarea(tarea)
            }
        }else{
            Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        // Configuración del RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val miAdaptador = AdaptadorTareas(clickListener = object : RVClickEvent{
            override fun itemClick(v: View?, data: Tarea) {
                val intent = Intent(applicationContext, DetallesTareaActivity::class.java)
                intent.putExtra("tarea", data)
                getResult.launch(intent)
            }
        })

        binding.recyclerView.adapter = miAdaptador

        binding.btnAddTarea.setOnClickListener {
            val intent = Intent(this, DetallesTareaActivity::class.java)
            getResult.launch(intent)
        }

        // Patron Observer para actualizar el RecyclerView con los cambios en el ViewModel
        val tareasObserver = Observer<List<Tarea>> {
            Log.d("Observer", "Insertado")
            miAdaptador.setTareas(it)
        }
        viewModel.listaTareas.observe(this, tareasObserver)
    }
}