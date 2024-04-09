
fun suma_dos_numeros(a: Int, b:Int) = a+b

fun suma_parejas_numeros(a: Int, b:Int, c: Int, d:Int) : Int{
    return suma_dos_numeros(suma_dos_numeros(a,b),suma_dos_numeros(c,d))
}

fun mostrar_suma_cuatro(a: Int, b:Int, c: Int, d:Int){
    println(suma_parejas_numeros(a,b,c,d))
}

fun main(){
    var resultado = mostrar_suma_cuatro(1,2,3,4)
    println(resultado)
}