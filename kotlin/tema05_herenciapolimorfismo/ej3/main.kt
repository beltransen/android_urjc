package tema05_herenciapolimorfismo.ej3

fun main(){
    // Hay que quitar al loro porque no se pueden crear instancias de Animal, al ser abstracta
    val mascotas : List<Animal> = listOf(Perro("Rex", "Pastor alemán"), Gato("López", 4)) // Borrado Animal("Loro"),
    for (bicho in mascotas){
        bicho.sonido()
        bicho.comer()
        //bicho.saludar() // Error! Los animales (clase base) no pueden saludar
    }
}