package urjc.lsmu.viewmodellivedata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import urjc.lsmu.viewmodellivedata.data.Pelicula

class AdaptadorPeliculas:
    RecyclerView.Adapter<AdaptadorPeliculas.MyViewHolder>() {
        var data: List<Pelicula> = emptyList()

    inner class MyViewHolder(val row: View): RecyclerView.ViewHolder(row){
        val txtTitulo = row.findViewById<TextView>(R.id.titulo)
        val txtDirector = row.findViewById<TextView>(R.id.director)
        val txtAnyo = row.findViewById<TextView>(R.id.anyo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.pelicula_item_lista, parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtTitulo.text = data.get(position).titulo
        holder.txtDirector.text = data.get(position).director
        holder.txtAnyo.text = data.get(position).anyo.toString()
    }

    override fun getItemCount(): Int = data.size

    fun setPeliculas(nuevaLista: List<Pelicula>){
        data = nuevaLista
        notifyDataSetChanged()
    }
}