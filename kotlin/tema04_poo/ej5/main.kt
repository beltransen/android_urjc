package tema04_poo.ej5

fun main(){
    val usu1 = Usuario("a@b.com", "01-01-1981")
    val usu2 = Usuario("a@b.com", "01-01-1981")
    val usu3 = Usuario("aaaa@b.com", "01-01-2091")

    println(usu1)
    val (email, fecha) = usu2
    println("$email $fecha")

    println("1 == 2 ${usu1==usu2}")
    println("1 == 3 ${usu1==usu3}")
    println("3 == 2 ${usu3==usu2}")
}