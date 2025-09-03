package com.example.miapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ElementosAdapter(
    private val elementos: List<ListasFragment.Elemento>,
    private val onElementoClick: (ListasFragment.Elemento) -> Unit
) : RecyclerView.Adapter<ElementosAdapter.ElementoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_elementos_adapter, parent, false)
        return ElementoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ElementoViewHolder, position: Int) {
        val elemento = elementos[position]
        holder.bind(elemento)
        holder.itemView.setOnClickListener { onElementoClick(elemento) }
    }

    override fun getItemCount(): Int = elementos.size

    class ElementoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titulo: TextView = itemView.findViewById(R.id.tvTitulo)
        private val descripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        private val icono: ImageView = itemView.findViewById(R.id.ivIcono)

        fun bind(elemento: ListasFragment.Elemento) {
            titulo.text = elemento.titulo
            descripcion.text = elemento.descripcion
            icono.setImageResource(elemento.icono)
        }
    }
}