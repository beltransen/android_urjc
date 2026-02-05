package tema01_intro

fun main(){
    println("Dame una temperatura en Farenheit")
    val far = readln().toFloat()

    println("Su equivalente en Celsius es ${(far-32)/1.8}")
}