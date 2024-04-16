package com.example.retrofitexample.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitexample.R
import com.example.retrofitexample.data.Pelicula

class PeliculasAdapter : RecyclerView.Adapter<PeliculasAdapter.MListHolder>() {

    inner class MListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<Pelicula>(){
        override fun areItemsTheSame(oldMovieDto: Pelicula, newMovieDto: Pelicula): Boolean {
            return oldMovieDto.id==newMovieDto.id
        }

        override fun areContentsTheSame(oldMovieDto: Pelicula, newMovieDto: Pelicula): Boolean {
            return oldMovieDto == newMovieDto
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MListHolder {
        return MListHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MListHolder, position: Int) {
        val movie = differ.currentList[position]
        val image = holder.itemView.findViewById<ImageView>(R.id.thumbnail)
        holder.itemView.apply {
            movie.posterURL.let{
                Glide.with(this).load("https://image.tmdb.org/t/p/original${movie.posterURL}").into(image)
            }
            holder.itemView.findViewById<TextView>(R.id.tituloPelicula).text = movie.titulo
            holder.itemView.findViewById<TextView>(R.id.puntuacionPelicula).text = movie.puntuacion.toString()
            holder.itemView.findViewById<TextView>(R.id.resumenPelicula).text = movie.descripcion
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}