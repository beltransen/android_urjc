package urjc.lsmu.ecommerce

fun main(){
    val v1 = Producto()
    println(v1.obtenerInformacion())
    val v2 = Producto("Jamon")
    println(v2.obtenerInformacion())
    val v3 = Producto("Jarra", 4.2)
    println(v3.obtenerInformacion())

    val miCliente = Cliente("jorge", "jorge@urjc.es")
    miCliente.anadirACesta(v1)
    miCliente.anadirACesta(v3)
    println("Total ${miCliente.calcularTotal()}")
    miCliente.mostrarInfo()
    val miCliente2 = Cliente("ana", )
    miCliente2.mostrarInfo()

    val miVIP = ClienteVIP("sergio", "s@asf.ca", 10.0)
    miVIP.anadirACesta(v1)
    miVIP.anadirACesta(v3)
    miVIP.mostrarInfo()
    println("Total VIP ${miVIP.calcularTotal()}")

}