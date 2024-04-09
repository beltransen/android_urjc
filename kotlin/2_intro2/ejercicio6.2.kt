fun ajusteTemperatura(temperatura: Int, operacion: (Int) -> Int): Int {
    return operacion(temperatura)
}

fun main(){
    val bajarTemperatura = {temp : Int -> temp-5}
    val subirTemperatura = {temp : Int -> temp+5}
    var temperatura = 10
    val objetivoTemp = 30
    temperatura = when{
        temperatura > objetivoTemp -> ajusteTemperatura(temperatura) { temp: Int -> temp - 5 }
        temperatura < objetivoTemp -> ajusteTemperatura(temperatura) { temp: Int -> temp + 5 }
        else -> temperatura
    }
}