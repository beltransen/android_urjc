fun main(){
    while (true){
        var palabra = readln()

        when(palabra){
            "stop", "parar" -> break
            else -> {
                var num = palabra.toInt()
                if (num > 0){
                    println("$num es un numero positivo")
                }else if (num<0){
                    println("$num es un numero negativo")
                }
            }
        }
    }
}