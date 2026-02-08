package urjc.lsmu.marcadorbasket

import androidx.lifecycle.ViewModel

class MarcadorViewModel: ViewModel() {
    var puntosA: Int = 0
        private set
    var puntosB: Int = 0
        private set

    fun incrementarMarcador(esA: Boolean, pts: Int){
        if(esA){
            puntosA += pts
        }else{
            puntosB += pts
        }
    }

    fun resetear(){
        puntosA = 0
        puntosB = 0
    }
}