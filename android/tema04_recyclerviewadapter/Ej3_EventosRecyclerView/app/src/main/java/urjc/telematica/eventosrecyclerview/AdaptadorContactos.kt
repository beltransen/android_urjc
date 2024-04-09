package urjc.telematica.eventosrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Solución para el adaptador del ejercicio 2 (sin eventos)
class AdaptadorContactos(val data: List<Contacto>):
    RecyclerView.Adapter<AdaptadorContactos.MyViewHolder>() {
    inner class MyViewHolder(val row: View): RecyclerView.ViewHolder(row){
        val txtNombre = row.findViewById<TextView>(R.id.contactoNombre)
        val txtTelefono = row.findViewById<TextView>(R.id.contactoTelefono)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.contacto_lista, parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtNombre.text = data.get(position).nombre
        holder.txtTelefono.text = data.get(position).telefono
    }

    override fun getItemCount(): Int = data.size

}