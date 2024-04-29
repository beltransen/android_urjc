package urjc.lsmu.mapasej3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import urjc.lsmu.mapasej3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var googleMap: GoogleMap // Para manejar el mapa
    // Lista inicial de ciudades
    private val localizaciones: MutableList<LatLng> = mutableListOf(
        LatLng(55.7558, 37.6173), // Moscu
        LatLng(48.8566, 2.3522), // Paris
        LatLng(51.5072, -0.1276), // Londres
        LatLng(4.7110, -74.0721), // Bogota
    )

    // Apunta a la ciudad actual. WARNING: mover a ViewModel para que no resetee al cambiar orientación
    private var indiceCiudad = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync { map ->
            googleMap = map
            googleMap.setOnMapLoadedCallback {
                try {
                    // Click listener. Se añade posición clickada a la
                    // lista de ciudades y se inserta marcador
                    googleMap.setOnMapClickListener { latLng ->
                        localizaciones.add(latLng)
                        googleMap.addMarker(
                            MarkerOptions().position(latLng)
                        )
                    }
                    siguienteCiudad(0) // Al iniciar la app, ir a la primera ciudad
                }catch (e: SecurityException){
                    Log.e("Location", e.message.toString())
                }
            }
        }

        // Se viaja a la siguiente ciudad de la lista
        binding.fab.setOnClickListener {
            siguienteCiudad()
        }

        // Se obtiene la posicion de la cámara del mapa y se añade un marcador ahí
        binding.btnMarcador.setOnClickListener {
            googleMap.addMarker(
                MarkerOptions().position(googleMap.cameraPosition.target)
            )
        }
    }

    // Función auxiliar para movernos a la siguiente ciudad de la lista
    private fun siguienteCiudad(indice: Int? = null, zoom: Float = 10f){
        if (indice != null){ // Si se fuerza una posición concreta
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localizaciones[indice], zoom))
            indiceCiudad = (indice+1)%localizaciones.size
        }else{ // Si no se pasa índice, ir a la siguiente
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localizaciones[indiceCiudad], zoom))
            indiceCiudad = (indiceCiudad+1)%localizaciones.size
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }
}