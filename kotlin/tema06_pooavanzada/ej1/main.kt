package tema06_pooavanzada.ej1

fun mostrarOcupacionEmpleado(dia: Dia, empleado: Empleado){
    when(dia){
        Dia.SABADO, Dia.DOMINGO -> println("${empleado.nombre} está descansando")
        else ->{
            if (empleado is Jefe && dia == Dia.VIERNES){
                empleado.librar()
            }else{
                empleado.trabajar()
            }
        }
    }
}

fun main(){
    val jefe = Jefe("marina")
    val junior = Junior("antón")

    mostrarOcupacionEmpleado(Dia.VIERNES, jefe)
    mostrarOcupacionEmpleado(Dia.VIERNES, junior)
    mostrarOcupacionEmpleado(Dia.SABADO, jefe)
    mostrarOcupacionEmpleado(Dia.SABADO, junior)
    mostrarOcupacionEmpleado(Dia.LUNES, jefe)
    mostrarOcupacionEmpleado(Dia.MARTES, junior)
}