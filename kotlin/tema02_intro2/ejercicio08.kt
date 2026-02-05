package tema02_intro2

fun tirarMoneda(a: Boolean): () -> Unit {
    return if(a){
        { println("Cara") }
    }else{
        { println("Cruz") }
    }
}

fun main(){
    val cara = tirarMoneda(true)
    val cruz = tirarMoneda(false)

    cara()
    cruz()
}