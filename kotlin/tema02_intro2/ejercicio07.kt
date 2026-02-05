package tema02_intro2

fun ajusteTemperatura2(temperatura: Int, operacion: (Int) -> Int): Int {
    return operacion(temperatura)
}

fun subirTemperatura2(temp: Int):Int = temp+5

fun main(){
    val bajarTemperatura = {temp : Int -> temp-5}
    val subirTemperatura = {temp : Int -> temp+5}
    var temperatura = 10
    val objetivoTemp = 30
    temperatura = when{
        temperatura > objetivoTemp -> ajusteTemperatura2(temperatura) { temp: Int -> temp - 5 }
        temperatura < objetivoTemp -> ajusteTemperatura2(temperatura, ::subirTemperatura2)
        else -> temperatura
    }
}