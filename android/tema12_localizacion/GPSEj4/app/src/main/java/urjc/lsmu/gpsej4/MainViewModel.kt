package urjc.lsmu.gpsej4

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    private val _location = MutableLiveData<Location?>()
    val location: LiveData<Location?> = _location

    private var updating = false

    private val locationRequest: LocationRequest = LocationRequest.Builder(1000)
        .setMinUpdateIntervalMillis(500)
        .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
        .setMinUpdateDistanceMeters(1f)
        .build()

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            val loc = locationResult.lastLocation
            if (loc != null) {
                _location.postValue(loc)
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation() {
        val currentLocationRequest = CurrentLocationRequest.Builder()
            .setDurationMillis(1000)
            .setMaxUpdateAgeMillis(2000)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .setGranularity(Granularity.GRANULARITY_COARSE)
            .build()

        fusedLocationClient.getCurrentLocation(currentLocationRequest, object : CancellationToken() {
            override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token
            override fun isCancellationRequested() = false
        })
            .addOnSuccessListener { location: Location? ->
                if (location == null) {
                    Log.d("MainViewModel", "Localización no disponible (getCurrentLocation)")
                    _location.postValue(null)
                } else {
                    _location.postValue(location)
                }
            }
            .addOnFailureListener { e ->
                Log.w("MainViewModel", "Error al obtener la localización puntual", e)
            }
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdates() {
        if (updating) return
        updating = true
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    fun stopLocationUpdates() {
        if (!updating) return
        updating = false
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    fun isUpdating(): Boolean = updating

    override fun onCleared() {
        stopLocationUpdates()
        super.onCleared()
    }
}

