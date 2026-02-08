package urjc.lsmu.marcadorbasket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import urjc.lsmu.marcadorbasket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val marcadorViewModel: MarcadorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myViewModel = marcadorViewModel

        binding.add1A.setOnClickListener{
            binding.myViewModel?.incrementarMarcador(true, 1)
            binding.marcadorA.text = marcadorViewModel.puntosA.toString()
        }
        binding.add2A.setOnClickListener{
            marcadorViewModel.incrementarMarcador(true, 2)
            binding.marcadorA.text = marcadorViewModel.puntosA.toString()
        }
        binding.add3A.setOnClickListener{
            marcadorViewModel.incrementarMarcador(true, 3)
            binding.marcadorA.text = marcadorViewModel.puntosA.toString()
        }

        binding.add1B.setOnClickListener{
            marcadorViewModel.incrementarMarcador(false, 1)
            binding.marcadorB.text = marcadorViewModel.puntosB.toString()
        }
        binding.add2B.setOnClickListener{
            marcadorViewModel.incrementarMarcador(false, 2)
            binding.marcadorB.text = marcadorViewModel.puntosB.toString()
        }
        binding.add3B.setOnClickListener{
            marcadorViewModel.incrementarMarcador(false, 3)
            binding.marcadorB.text = marcadorViewModel.puntosB.toString()
        }

        binding.btnReset.setOnClickListener {
            marcadorViewModel.resetear()
        }
    }
}