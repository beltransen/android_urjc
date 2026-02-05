package tema01_intro

fun main(){
    var index = 1
    while(index<101){
        when{
            index%3==0 && index%5==0 -> println("FizzBuzz")
            index%3==0 -> println("Fizz")
            index%5==0 -> println("Buzz")
            else -> println(index)
        }
        index++
    }
}