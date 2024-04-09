package urjc.lsmu.marcadorbasket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import urjc.lsmu.marcadorbasket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.puntosA = 0
        binding.puntosB = 0

        binding.add1A.setOnClickListener{
            binding.puntosA+=1
        }
        binding.add2A.setOnClickListener{
            binding.puntosA+=2
        }
        binding.add3A.setOnClickListener{
            binding.puntosA+=3
        }

        // TODO Añadir funcionalidad de los botones del equipo B

        binding.btnReset.setOnClickListener {
            binding.puntosA = 0
            binding.puntosB = 0
        }

    }
}