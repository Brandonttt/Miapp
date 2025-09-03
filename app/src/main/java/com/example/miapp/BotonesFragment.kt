package com.example.miapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class BotonesFragment : Fragment() {

    private var contadorClicks = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_botones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Button normal
        val botonNormal = view.findViewById<Button>(R.id.botonNormal)
        botonNormal.setOnClickListener {
            Toast.makeText(context, "¡Has pulsado el botón normal!", Toast.LENGTH_SHORT).show()
        }

        // Button con estilo personalizado
        val botonPersonalizado = view.findViewById<Button>(R.id.botonPersonalizado)
        botonPersonalizado.setOnClickListener {
            Snackbar.make(view, "¡Botón con estilo personalizado pulsado!", Snackbar.LENGTH_LONG).show()
        }

        // ImageButton
        val imageButton = view.findViewById<ImageButton>(R.id.imageButton)
        val contadorImageButton = view.findViewById<TextView>(R.id.contadorImageButton)

        imageButton.setOnClickListener {
            contadorClicks++
            contadorImageButton.text = "Clicks: $contadorClicks"
        }

        // FloatingActionButton
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            Toast.makeText(context, "¡Has pulsado el Floating Action Button!", Toast.LENGTH_SHORT).show()
        }

        // Toggle Button
        val toggleButton = view.findViewById<Button>(R.id.toggleButton)
        toggleButton.setOnClickListener {
            if (toggleButton.text == "ON") {
                toggleButton.text = "OFF"
                toggleButton.setBackgroundColor(resources.getColor(android.R.color.darker_gray, null))
            } else {
                toggleButton.text = "ON"
                toggleButton.setBackgroundColor(resources.getColor(android.R.color.holo_green_light, null))
            }
        }
    }
}