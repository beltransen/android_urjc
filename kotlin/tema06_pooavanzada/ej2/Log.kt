package tema06_pooavanzada.ej2

object Log {
    private val historial: MutableList<String> = mutableListOf()
    fun info(msg: String){
        val str: String = "INFO: $msg"
        historial.add(str)
        println(str)
    }

    fun error(msg: String){
        val str: String = "ERROR: $msg"
        historial.add(str)
        println(str)
    }

    fun history(){
        println("Historial de logs:")
        for(l in historial){
            println(l)
        }
    }
}