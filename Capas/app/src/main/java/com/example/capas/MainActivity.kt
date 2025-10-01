package com.example.capas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Agregar fragmento de información
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.info_container, BodyInfoFragment.newInstance("Cuerpo Humano",
                    "Toca una región del cuerpo para explorar más detalles"))
                .commit()
        }

        // Configurar las regiones interactivas
        setupClickableRegions()
    }

    private fun setupClickableRegions() {
        val headRegion = findViewById<View>(R.id.head_region)
        val torsoRegion = findViewById<View>(R.id.torso_region)
        val legsRegion = findViewById<View>(R.id.legs_region)

        headRegion.setOnClickListener {
            navigateToBodySection("Cabeza", "La cabeza contiene el cerebro y órganos sensoriales principales.",
                R.drawable.head_detail, it)
        }

        torsoRegion.setOnClickListener {
            navigateToBodySection("Torso", "El torso alberga órganos vitales como el corazón y pulmones.",
                R.drawable.torso_detail, it)
        }

        legsRegion.setOnClickListener {
            navigateToBodySection("Piernas", "Las piernas permiten la locomoción y soportan el peso corporal.",
                R.drawable.legs_detail, it)
        }
    }

    private fun navigateToBodySection(title: String, description: String, imageResId: Int, view: View) {
        val intent = Intent(this, BodySectionActivity::class.java).apply {
            putExtra("SECTION_TITLE", title)
            putExtra("SECTION_DESCRIPTION", description)
            putExtra("SECTION_IMAGE", imageResId)
        }

        // Crear una transición animada
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            view,
            "shared_body_transition"
        )

        startActivity(intent, options.toBundle())
    }
}