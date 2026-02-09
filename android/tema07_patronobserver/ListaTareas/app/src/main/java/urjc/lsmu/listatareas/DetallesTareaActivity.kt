package urjc.lsmu.listatareas

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import urjc.lsmu.listatareas.data.Tarea
import urjc.lsmu.listatareas.databinding.ActivityDetallesTareaBinding

class DetallesTareaActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetallesTareaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_detalles_tarea)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalles_tarea)

        var tarea = intent.extras?.get("tarea") as? Tarea
        if (tarea != null) {
            binding.detalleTitulo.text = Editable.Factory.getInstance().newEditable(tarea.titulo)
            binding.detalleDescripcion.text = Editable.Factory.getInstance().newEditable(tarea.descripcion)
            binding.detalleCompletada.text = if (tarea.completada) "Completada" else "No completada"
            binding.detalleCompletada.setBackgroundColor(if (tarea.completada) getColor(android.R.color.holo_green_dark) else getColor(android.R.color.holo_red_dark))
        }

        binding.btnGuardar.setOnClickListener({
            if (tarea != null) {
                tarea!!.titulo = binding.detalleTitulo.text.toString()
                tarea!!.descripcion = binding.detalleDescripcion.text.toString()
            }else{
                tarea = Tarea(
                    titulo = binding.detalleTitulo.text.toString(),
                    descripcion = binding.detalleDescripcion.text.toString(),
                    completada = false
                )
            }
            val intent = Intent()
            intent.putExtra("tarea", tarea)
            setResult(RESULT_OK, intent)
            finish()
        })
    }
}