package tema04_poo.ej6

class Cliente(val nombre: String, var email: String? = null) {
    val cesta: MutableList<Producto> = mutableListOf()

    fun anadirACesta(producto: Producto){
        cesta.add(producto)
    }

    fun calcularTotal(): Double{
        var total = 0.0
        for (p in cesta){
            total+=p.precio
        }
        return total
    }

    fun mostrarCesta(){
        for ((n, p) in cesta){ // Usa los operadores component para descomponer un producto en nombre y precio
            println("$n: $p euros")
        }
    }

    fun mostrarInfo(){
        println("$nombre (${email ?: "Email no disponible"}). ${cesta.count()} ítems em la lista")
    }
}