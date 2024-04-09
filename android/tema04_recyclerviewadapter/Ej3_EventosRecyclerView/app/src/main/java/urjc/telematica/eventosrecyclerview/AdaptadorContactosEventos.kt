package urjc.telematica.eventosrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/* Definimos dos interfaces, una por evento que queremos soportar en el adaptador.
 * El nombre de los métodos y sus parámetros son customizables.
 * Eso sí, los parámetros tienen que poder enviarse desde el evento captado por la vista en el ViewHolder.
 */
interface RVClickEvent {
    fun itemClick(v: View?, data: Contacto) // Permite a la función usar la vista y el dato de la fila concreta
}

interface RVLongClickEvent {
    fun itemLongClick(position: Int) // Permite a la función usar únicamente la posición del elemento en la colección de datos
}

// Este adaptador recibe dos listener, uno para cada evento (click simple y largo)
class AdaptadorContactosEventos(val data: List<Contacto>, val clickListener: RVClickEvent? = null, val longClickListener: RVLongClickEvent? = null):
    RecyclerView.Adapter<AdaptadorContactosEventos.MyViewHolder>() {

    // El ViewHolder implementa dos interfaces> click y longclick listener
    inner class MyViewHolder(val row: View): RecyclerView.ViewHolder(row), View.OnClickListener, View.OnLongClickListener {
        val txtNombre = row.findViewById<TextView>(R.id.contactoNombre)
        val txtTelefono = row.findViewById<TextView>(R.id.contactoTelefono)

        init {
            row.setOnClickListener(this)
            row.setOnLongClickListener(this)
        }

        // Obligatorio al implementar View.OnClickListener
        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                clickListener?.itemClick(v, data[position]) // En este caso, pasamos la vista y el dato a la función recibida
            }
        }

        // Obligatorio al implementar View.OnLongClickListener
        override fun onLongClick(v: View?): Boolean {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                longClickListener?.itemLongClick(position) // En este caso, pasamos la posición a la función recibida
            }
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.contacto_lista, parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtNombre.text = data[position].nombre
        holder.txtTelefono.text = data[position].telefono
    }

    override fun getItemCount(): Int = data.size

}