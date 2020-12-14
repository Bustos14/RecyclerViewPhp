package com.example.recyclerviewkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewkotlin.Modelos.Actores
import kotlinx.android.synthetic.main.list_actores.view.*
import java.lang.IllegalArgumentException

class adaptador(val listaActores: ArrayList<Actores>) :
    RecyclerView.Adapter<adaptador.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adaptador.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_actores, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: adaptador.ViewHolder, position: Int) {
        holder.bindItems(listaActores[position])
    }

    override fun getItemCount(): Int {
        return listaActores.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(actores: Actores) {
            val nombre = itemView.findViewById(R.id.tx_nombre) as TextView
            val serie = itemView.findViewById(R.id.tx_Pelicula) as TextView
            val edad = itemView.findViewById(R.id.tx_edad) as TextView
            val imagen = itemView.findViewById(R.id.img_actor) as ImageView
            nombre.text ="Nombre: "+ actores.nombre
            serie.text = "Protagonizó: \n"+actores.pelicula
            edad.text = "Edad: "+actores.edad
            Glide.with(itemView).load(actores.imagen).into(imagen)

        }
    }
}