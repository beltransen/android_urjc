package urjc.lsmu.marcadorbasket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MarcadorViewModel: ViewModel() {
    private val _puntosA = MutableLiveData<Int>(0)
    val puntosA: LiveData<Int>
        get() = _puntosA

    private val _puntosB = MutableLiveData<Int>(0)
    val puntosB: LiveData<Int>
        get() = _puntosB

    fun incrementarMarcador(esA: Boolean, pts: Int){
        if(esA){
            _puntosA.value = _puntosA.value!! + pts
        }else{
            _puntosB.value = _puntosB.value!! + pts
        }
    }

    fun resetear(){
        _puntosA.value = 0
        _puntosB.value = 0
    }
}