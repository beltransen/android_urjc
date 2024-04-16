package urjc.lsmu.repoasyncexample.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Clase Factory para instanciar el ViewModel (recibe el mismo parámetro que el ViewModel y se lo pasa al crearlo)
class PeliculasViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PeliculasViewModel(context) as T
    }
}