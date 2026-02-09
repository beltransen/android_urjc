package urjc.lsmu.bibliotecaddbbvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import urjc.lsmu.bibliotecaddbbvm.data.source.local.LibroEntity

/* Definimos dos interfaces, una por evento que queremos soportar en el adaptador.
 * El nombre de los métodos y sus parámetros son customizables.
 * Eso sí, los parámetros tienen que poder enviarse desde el evento captado por la vista en el ViewHolder.
 */
interface RVClickEvent {
    fun itemClick(v: View?, data: LibroEntity) // Permite a la función usar la vista y el dato de la fila concreta
}

interface RVLongClickEvent {
    fun itemLongClick(v: View?, data: LibroEntity) // Permite a la función usar la vista y el dato de la fila concreta
}

class AdaptadorLibros(val clickListener: RVClickEvent? = null, val longClickListener: RVLongClickEvent? = null):
    RecyclerView.Adapter<AdaptadorLibros.MyViewHolder>() {
        var data: List<LibroEntity> = emptyList()

    inner class MyViewHolder(val row: View): RecyclerView.ViewHolder(row), View.OnClickListener,
        View.OnLongClickListener {
        val txtTitulo = row.findViewById<TextView>(R.id.titulo)
        val txtAnyo = row.findViewById<TextView>(R.id.anyo)
        val txtAutor = row.findViewById<TextView>(R.id.autor)

        init {
            row.setOnClickListener(this)
            row.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                clickListener?.itemClick(v, data[position]) // En este caso, pasamos la vista y el dato a la función recibida
            }
        }


        override fun onLongClick(v: View?): Boolean {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                longClickListener?.itemLongClick(v, data[position]) // En este caso, pasamos la vista y el dato a la función recibida
                return true
            }
            return false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.libro_item_lista, parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtTitulo.text = data.get(position).titulo
        holder.txtAnyo.text = data.get(position).anyo.toString()
        holder.txtAutor.text = data.get(position).autor
    }

    override fun getItemCount(): Int = data.size

    fun setLibros(nuevaLista: List<LibroEntity>){
        data = nuevaLista
        notifyDataSetChanged()
    }
}