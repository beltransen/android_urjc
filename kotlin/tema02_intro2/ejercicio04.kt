package tema02_intro2

fun esDomingo(dia : String) = dia == "domingo"
fun estaDemasiadoCaliente(temp : Int) = temp > 30
fun estaSucia(suciedad : Int) = suciedad > 50

fun deberiaCambiarAguaWhen(dia : String, temp : Int = 22, suciedad : Int = 30) : Boolean{
    return when{
        esDomingo(dia) -> true
        estaDemasiadoCaliente(temp) -> true
        estaSucia(suciedad) -> true
        else -> false
    }
}

fun main(){
    println(deberiaCambiarAguaWhen("domingo"))
    println(deberiaCambiarAguaWhen("lunes", 45))
    println(deberiaCambiarAguaWhen("lunes", suciedad = 51))
    println(deberiaCambiarAguaWhen("lunes", 21, 25))
}