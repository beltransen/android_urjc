const val PI = 3.1415

fun main(){
    println("Introduce el radio del círculo")
    val radio = readln().toFloat()

    var perimetro = 2 * PI * radio // Variable
    val area =  PI * radio * radio // Inmutable

    println("El perimetro es $perimetro y el area es $area")
}