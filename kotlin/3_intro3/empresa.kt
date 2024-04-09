
//val listaTrabajadores: MutableMap<String, Int> = mutableMapOf() OTRA OPCIÓN CON UNA VARIABLE GLOBAL Y NO PASANDO LA LISTA COMO PARAM

fun agregarTrabajador(lista: MutableList<Map<String,Any>>, nombre: String, sueldo: Int): Int{
    val mapa = mapOf("nombre" to nombre, "nomina" to sueldo)
    lista.add(mapa)
    return lista.size
}

fun visualizarEjecutivos(lista: MutableList<Map<String,Any>>){
    for ((index, item) in lista.withIndex()){
        if(item["nomina"] as Int > 3000){
            println("Ejecutivo $index: ${item["nombre"]}")
        }
    }
}

fun main(){
    val listaTrabajadores = mutableListOf<Map<String,Any>>()

    // Añadir trabajadores
    agregarTrabajador(listaTrabajadores, "Alejandra", 3250)
    agregarTrabajador(listaTrabajadores, "Víctor", 1680)
    agregarTrabajador(listaTrabajadores, "William", 2400)
    println("Total trabajadores ${agregarTrabajador(listaTrabajadores, "Lucy", 4750)}")

    visualizarEjecutivos(listaTrabajadores)
}
