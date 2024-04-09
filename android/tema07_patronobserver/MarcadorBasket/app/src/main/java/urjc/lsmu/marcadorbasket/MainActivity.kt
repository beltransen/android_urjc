package urjc.lsmu.marcadorbasket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import urjc.lsmu.marcadorbasket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val marcadorViewModel: MarcadorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myViewModel = marcadorViewModel
        binding.lifecycleOwner = this // Asociacion directa XML-LiveData (Ejercicio 2)

        // Observers explícitos (Ejercicio 1)
        /*val puntosA_observer = Observer<Int>{ nuevoValor ->
            binding.marcadorA.text = nuevoValor.toString()
        }
        marcadorViewModel.puntosA.observe(this, puntosA_observer)

        val puntosB_observer = Observer<Int>{ nuevoValor ->
            binding.marcadorB.text = nuevoValor.toString()
        }
        marcadorViewModel.puntosB.observe(this, puntosB_observer)*/


        binding.add1A.setOnClickListener{
            binding.myViewModel?.incrementarMarcador(true, 1)
        }
        binding.add2A.setOnClickListener{
            marcadorViewModel.incrementarMarcador(true, 2)
        }
        binding.add3A.setOnClickListener{
            marcadorViewModel.incrementarMarcador(true, 3)
        }

        binding.add1B.setOnClickListener{
            marcadorViewModel.incrementarMarcador(false, 1)
        }
        binding.add2B.setOnClickListener{
            marcadorViewModel.incrementarMarcador(false, 2)
        }
        binding.add3B.setOnClickListener{
            marcadorViewModel.incrementarMarcador(false, 3)
        }

        binding.btnReset.setOnClickListener {
            marcadorViewModel.resetear()
        }

    }
}