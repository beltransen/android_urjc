package tema05_herenciapolimorfismo.ej2

open class Cliente(val nombre: String, var email: String? = null) {
    val cesta: MutableList<Producto> = mutableListOf()

    fun anadirACesta(producto: Producto){
        cesta.add(producto)
    }

    open fun calcularTotal(): Double{
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

class ClienteVIP(name: String, correo: String, val descuento: Double = 0.0): Cliente(name, correo){
    override fun calcularTotal(): Double {
        return super.calcularTotal() * (100-descuento)/100
    }
//    override fun calcularTotal(): Double{
//        var total: Double = 0.0
//        for (item in cesta){
//            total+=item.precio
//        }
//        return total * (100-descuento)/100
//    }
}