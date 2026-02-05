package tema06_pooavanzada.ej4

infix fun String.concat(other: String) = "$this$other"
fun String.reverse() = this.reversed()

fun main(){
    println(("Jorge" concat "Beltran").reverse())
}