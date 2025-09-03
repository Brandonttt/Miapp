package com.example.miapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment

class InformacionFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var progressBarCircular: ProgressBar
    private lateinit var progressText: TextView
    private lateinit var handler: Handler
    private var progressStatus = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_informacion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TextView con formato
        val textViewFormatted = view.findViewById<TextView>(R.id.textViewFormatted)
        textViewFormatted.text = resources.getString(
            R.string.texto_formateado,
            "Android Studio",
            "Kotlin"
        )

        // ImageView con cambio de imagen
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val btnCambiarImagen = view.findViewById<Button>(R.id.btnCambiarImagen)
        var imagenActual = 1

        btnCambiarImagen.setOnClickListener {
            if (imagenActual == 1) {
                imageView.setImageResource(android.R.drawable.dialog_holo_light_frame)
                imagenActual = 2
            } else {
                imageView.setImageResource(android.R.drawable.dialog_holo_dark_frame)
                imagenActual = 1
            }
        }

        // ProgressBar
        progressBar = view.findViewById(R.id.progressBar)
        progressBarCircular = view.findViewById(R.id.progressBarCircular)
        progressText = view.findViewById(R.id.progressText)
        val btnIniciarProgress = view.findViewById<Button>(R.id.btnIniciarProgress)

        handler = Handler(Looper.getMainLooper())

        btnIniciarProgress.setOnClickListener {
            progressStatus = 0
            progressBarCircular.visibility = View.VISIBLE

            Thread {
                while (progressStatus < 100) {
                    progressStatus += 1
                    handler.post {
                        progressBar.progress = progressStatus
                        progressText.text = "$progressStatus%"

                        if (progressStatus >= 100) {
                            progressBarCircular.visibility = View.GONE
                        }
                    }
                    try {
                        Thread.sleep(50)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }.start()
        }
    }
}