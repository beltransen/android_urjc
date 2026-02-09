package urjc.lsmu.gpsej4

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import urjc.lsmu.gpsej4.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var locationPermissionRequest: ActivityResultLauncher<Array<String>>
    val viewModel: MainViewModel by viewModels()

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Configurar la petición de permisos
        locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    binding.tvGps.text = getString(R.string.exito)
                    // Opcionalmente, obtener la ubicación inmediatamente tras conceder el permiso
                    viewModel.getCurrentLocation()
                }
                else -> { // Solo localización aproximada o ningún permiso concedido
                    binding.tvGps.text = getString(R.string.fracaso)
                }
            }
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED){
            Log.d("Permisos", "La app tiene los permisos necesarios")
            binding.tvGps.text = getString(R.string.exito)
        }else{ // Solo localización aproximada o ningún permiso concedido
            Log.d("Permisos", "La app no tiene los permisos necesarios")
            binding.tvGps.text = getString(R.string.fracaso)

            // Hay que pedir los permisos
            locationPermissionRequest.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION))
        }

        // Observamos la ubicación expuesta por el ViewModel
        viewModel.location.observe(this) { location: Location? ->
            if (location == null) {
                // No hay localización aún
                binding.tvTiempogps.text = ""
            } else {
                binding.tvGps.text = String.format(Locale.getDefault(), "%.6f, %.6f", location.latitude, location.longitude)
                val sdf = SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.getDefault())
                sdf.timeZone= TimeZone.getTimeZone("Europe/Madrid")
                binding.tvTiempogps.text = sdf.format(location.time)
            }
        }

        binding.fab.setOnClickListener {
            // Delegar al ViewModel la obtención puntual de localización
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, getString(R.string.no_permisos_localizacion), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.getCurrentLocation()
        }

        binding.btnToggleLive.setOnClickListener {
            // Alternar actualizaciones continuas a través del ViewModel
            if (viewModel.isUpdating()){
                viewModel.stopLocationUpdates()
                binding.btnToggleLive.setBackgroundColor(resources.getColor(android.R.color.holo_green_dark, theme))
                binding.btnToggleLive.text = resources.getText(R.string.encender)
            }else{
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, getString(R.string.no_permisos_localizacion), Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                viewModel.startLocationUpdates()
                binding.btnToggleLive.setBackgroundColor(resources.getColor(android.R.color.holo_red_dark, theme))
                binding.btnToggleLive.text = resources.getText(R.string.apagar)
            }
        }
    }
}