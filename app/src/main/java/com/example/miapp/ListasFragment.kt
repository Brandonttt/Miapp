package com.example.miapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_listas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar ListView
        val listView = view.findViewById<ListView>(R.id.listView)
        val paises = arrayOf(
            "España", "México", "Argentina", "Colombia",
            "Chile", "Perú", "Ecuador", "Venezuela",
            "Uruguay", "Paraguay", "Bolivia", "Costa Rica"
        )

        val adaptadorListView = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            paises
        )

        listView.adapter = adaptadorListView

        listView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                context,
                "Has seleccionado: ${paises[position]}",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Configurar RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val elementos = listOf(
            Elemento("Android Studio", "IDE para desarrollo Android", android.R.drawable.ic_menu_compass),
            Elemento("Kotlin", "Lenguaje de programación oficial para Android", android.R.drawable.ic_menu_manage),
            Elemento("Java", "Lenguaje también usado en Android", android.R.drawable.ic_menu_edit),
            Elemento("XML", "Lenguaje de marcado para layouts", android.R.drawable.ic_menu_sort_by_size),
            Elemento("Gradle", "Sistema de construcción", android.R.drawable.ic_menu_preferences),
            Elemento("Material Design", "Guía de diseño de Google", android.R.drawable.ic_menu_gallery)
        )

        val adaptadorRecyclerView = ElementosAdapter(elementos) { elemento ->
            Toast.makeText(
                context,
                "Has seleccionado: ${elemento.titulo}",
                Toast.LENGTH_SHORT
            ).show()
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adaptadorRecyclerView
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    data class Elemento(val titulo: String, val descripcion: String, val icono: Int)
}