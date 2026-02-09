package urjc.lsmu.listatareas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import urjc.lsmu.listatareas.data.Tarea

interface RVClickEvent {
    fun itemClick(v: View?, data: Tarea) // Permite a la función usar la vista y el dato de la fila concreta
}

class AdaptadorTareas(val clickListener: RVClickEvent? = null):
    RecyclerView.Adapter<AdaptadorTareas.MyViewHolder>() {
        var data: List<Tarea> = emptyList()

    inner class MyViewHolder(val row: View): RecyclerView.ViewHolder(row){
        val txtTitulo = row.findViewById<TextView>(R.id.titulo)
        var checkCompletada = row.findViewById<CheckBox>(R.id.completada)

        init {
            txtTitulo.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener?.itemClick(txtTitulo, data[position])
                }
            }

            checkCompletada.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    data[position].completada = checkCompletada.isChecked
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.tarea_item_lista, parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtTitulo.text = data.get(position).titulo
        holder.checkCompletada.isChecked = data.get(position).completada
    }

    override fun getItemCount(): Int = data.size

    fun setTareas(nuevaLista: List<Tarea>){
        data = nuevaLista
        notifyDataSetChanged()
    }
}