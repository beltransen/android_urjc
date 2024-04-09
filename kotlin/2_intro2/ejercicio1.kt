fun main(args: Array<String>) {
    var palabras = listOf("lapiz", "mando", "cumbre", "rubio", "dsfsdf", "sdfsf")

    // recorre los elementos pares.
    etiqueta@for (x in 1..<palabras.size step 2){
        //Para cada elemento, imprime sus letras en orden inverso.
        for (y in palabras[x].length-1 downTo 0){
            print(palabras[x][y])

            //Si la letra imprimir es una ‘a’, se pasa a la siguiente palabra.
            if (palabras[x][y]=='a') break

            //Si la letra es una ‘u’, se debe parar de imprimir.
            if (palabras[x][y]=='u') break@etiqueta
        }
        println()

    }

}