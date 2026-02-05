package tema04_poo.ej6

fun main(){
    val p1 = Producto("Gorra", 12.99)
    val p2 = Producto("Bufanda", 15.0)
    val p3 = Producto("Boli")
    p1.obtenerInformacion()
    p2.obtenerInformacion()
    println(p3.obtenerInformacion())

    val c1 = Cliente("Jorge", "jorge@xyz.com")
    val c2 = Cliente("Mary")
    c1.anadirACesta(p1)
    c1.anadirACesta(p3)
    println("Total en la cesta: ${c1.calcularTotal()}")
    println(c1)

    c1.mostrarCesta()
    c1.mostrarInfo()

    c2.mostrarInfo()
}