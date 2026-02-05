package tema02_intro2
fun deberiaCambiarAgua(dia : String, temp : Int = 22, suciedad : Int = 30) : Boolean{
    if (dia == "domingo" || temp > 30 || suciedad >50){
        return true
    }
    return false
}


fun main(){
    println(deberiaCambiarAgua("domingo"))
    println(deberiaCambiarAgua("lunes", 45))
    println(deberiaCambiarAgua("lunes", suciedad = 51))
    println(deberiaCambiarAgua("lunes", 21, 25))
}