package urjc.lsmu.gpsej3

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
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import urjc.lsmu.gpsej3.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.TimeZone

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var locationPermissionRequest: ActivityResultLauncher<Array<String>>
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest // Requisitos para las actualizaciones
    private lateinit var locationCallback: LocationCallback // Llamada cuando llega una nueva posicion
    private var gpsUpdates = false

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
                    binding.tvGps.text = "EXITO"
                } else -> { // Solo localización aproximada o ningún permiso concedido
                binding.tvGps.text = "FRACASO"
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
            binding.tvGps.text = "EXITO"
        }else{ // Solo localización aproximada o ningún permiso concedido
            Log.d("Permisos", "La app no tiene los permisos necesarios")
            binding.tvGps.text = "FRACASO"

            // Hay que pedir los permisos
            locationPermissionRequest.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION))
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext)

        binding.fab.setOnClickListener {
            // Opcion 1: última posición recibida por alguna app
            /*val posicion = fusedLocationClient.lastLocation.addOnSuccessListener {location ->
                if (location == null) { // GPS apagado
                    Toast.makeText(this, "No se puede obtener la localización", Toast.LENGTH_SHORT)
                        .show()
                }else {
                    Log.d("Location", "%.6f, %.6f".format(location.latitude, location.longitude))
                    binding.tvGps.text = "${location.latitude}, ${location.longitude}"
                    val sdf = SimpleDateFormat("yy-MM-dd kk:mm:ss")
                    sdf.timeZone= TimeZone.getTimeZone("Europe/Madrid")
                    binding.tvTiempogps.text = sdf.format(location.time)
                }
            }

            // Opcion 2: posicion actual (default)
            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token
                override fun isCancellationRequested() = false
            })
                .addOnSuccessListener { location: Location? ->
                    if (location == null) { // GPS apagado o no resuelto a tiempo
                        Toast.makeText(this, "Localización no disponible", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Log.d("Location", "%.6f, %.6f".format(location.latitude, location.longitude))
                        binding.tvGps.text = "${location.latitude}, ${location.longitude}"
                        val sdf = SimpleDateFormat("yy-MM-dd kk:mm:ss")
                        sdf.timeZone= TimeZone.getTimeZone("Europe/Madrid")
                        binding.tvTiempogps.text = sdf.format(location.time)
                    }
                }*/

            // Opcion 3: posicion actual (petición customizada)
            val currentLocationRequest = CurrentLocationRequest.Builder()
                .setDurationMillis(1000) // Duración máxima de la petición. Si no, devuelve null
                .setMaxUpdateAgeMillis(2000) // Edad máxima de la posición devuelta
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY) // Relación precisión-consumo
                .setGranularity(Granularity.GRANULARITY_COARSE) // Calidad de la posición obtenida
                .build()

            fusedLocationClient.getCurrentLocation(currentLocationRequest, object : CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token
                override fun isCancellationRequested() = false
            })
                .addOnSuccessListener { location: Location? ->
                    if (location == null) { // GPS apagado o no resuelto a tiempo
                        Toast.makeText(this, "Localización no disponible", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        binding.tvGps.text = "%.6f, %.6f".format(location.latitude, location.longitude)
                        val sdf = SimpleDateFormat("yy-MM-dd kk:mm:ss")
                        sdf.timeZone= TimeZone.getTimeZone("Europe/Madrid")
                        binding.tvTiempogps.text = sdf.format(location.time)
                    }
                }
        }

        // Configuración de la solicitud de localización
        locationRequest = LocationRequest.Builder(1000) // Intervalo al que queremos recibir datos
            .setMinUpdateIntervalMillis(500) // Intervalo más rápido para recibirlos
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY) // Relación precisión-consumo
            .setMinUpdateDistanceMeters(1f) // Distancia entre posiciones para dar una actualizacion
            .build()

        // Definición del callback para gestionar la actualización recibida
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                // Comprobación extra para ver que nos llega una posición y no viene vacía
                if (locationResult.lastLocation != null){
                    val loc = locationResult.lastLocation!!
                    binding.tvGps.text = "%.6f, %.6f".format(loc.latitude, loc.longitude)
                    val sdf = SimpleDateFormat("yy-MM-dd kk:mm:ss")
                    sdf.timeZone= TimeZone.getTimeZone("Europe/Madrid")
                    binding.tvTiempogps.text = sdf.format(loc.time)
                }
            }
        }

        binding.btnToggleLive.setOnClickListener {
            if (gpsUpdates){
                gpsUpdates = false
                // Parar la recepción de actualizaciones de localización
                fusedLocationClient.removeLocationUpdates(locationCallback)
                binding.btnToggleLive.setBackgroundColor(resources.getColor(android.R.color.holo_green_dark, theme))
                binding.btnToggleLive.text = resources.getText(R.string.encender)
            }else{
                gpsUpdates = true
                // Registrar la petición de actualizaciones de localizacion
                fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
                binding.btnToggleLive.setBackgroundColor(resources.getColor(android.R.color.holo_red_dark, theme))
                binding.btnToggleLive.text = resources.getText(R.string.apagar)
            }
        }
    }
}