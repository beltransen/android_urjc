package tema02_intro2

fun main(){
    val suma_dos_numeros: (Int,Int) -> Int = {a,b-> a+b}
    val suma_parejas_numeros = {a: Int, b:Int, c: Int, d:Int ->
        suma_dos_numeros(suma_dos_numeros(a,b),suma_dos_numeros(c,d))
    }
    val mostrar_suma_cuatro: (Int, Int, Int, Int) -> Unit = { a, b, c, d -> println(suma_parejas_numeros(a,b,c,d)) }

    mostrar_suma_cuatro(1,2,3,4)
}