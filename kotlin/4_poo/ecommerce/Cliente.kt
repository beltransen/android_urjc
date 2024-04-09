package urjc.lsmu.ecommerce

open class Cliente(val nombre: String, val email: String? = null) {
    val cesta: MutableList<Producto> = mutableListOf()

    fun anadirACesta(producto: Producto){
        cesta.add(producto)
    }

    open fun calcularTotal(): Double{
        var total: Double = 0.0
        for (item in cesta){
            total+=item.precio
        }
        return total
    }

    fun mostrarInfo(){
        println("Cliente $nombre [${email?:"Email no disponible"}] tiene en la cesta ${cesta.count()}")
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