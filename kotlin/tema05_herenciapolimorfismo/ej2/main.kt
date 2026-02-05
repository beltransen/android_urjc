package tema05_herenciapolimorfismo.ej2

fun main(){
    val v1 = Producto()
    println(v1.obtenerInformacion())
    val v2 = Producto("Jamon")
    println(v2.obtenerInformacion())
    val v3 = Producto("Jarra", 4.2)
    println(v3.obtenerInformacion())

    val miCliente = Cliente("jorge", "jorge@xyz.com")
    miCliente.anadirACesta(v1)
    miCliente.anadirACesta(v3)
    miCliente.mostrarInfo()
    println("Total ${miCliente.calcularTotal()}")
    miCliente.mostrarInfo()
    val miCliente2 = Cliente("ana")
    miCliente2.mostrarInfo()

    val miVIP = ClienteVIP("sergio", "s@asf.ca", 10.0)
    miVIP.anadirACesta(v1)
    miVIP.anadirACesta(v3)
    miVIP.mostrarInfo()
    println("Total VIP ${miVIP.calcularTotal()}")
}