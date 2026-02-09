package urjc.telematica.ej3contactosfabnavdrawer

import java.io.Serializable

data class Contacto(val nombre: String, val telefono: String, val email: String, val nacimiento: String) : Serializable