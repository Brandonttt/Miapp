package com.example.capas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BodySectionActivity : AppCompatActivity() {

    private lateinit var sectionTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_body_section)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.section_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recibir datos del intent
        sectionTitle = intent.getStringExtra("SECTION_TITLE") ?: "Sección del Cuerpo"
        val sectionDescription = intent.getStringExtra("SECTION_DESCRIPTION") ?: ""
        val sectionImageId = intent.getIntExtra("SECTION_IMAGE", R.drawable.body_default)

        // Configurar vista
        findViewById<TextView>(R.id.section_title).text = sectionTitle
        findViewById<ImageView>(R.id.section_image).setImageResource(sectionImageId)

        // Agregar fragmento de información
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.section_info_container,
                    BodyInfoFragment.newInstance(sectionTitle, sectionDescription))
                .commit()
        }

        // Configurar puntos de interés según la sección
        setupPointsOfInterest()
    }

    private fun setupPointsOfInterest() {
        // Layout de puntos de interés que dependerá de la sección seleccionada
        val poiContainer = findViewById<View>(R.id.points_of_interest_container)

        // Ocultar todos los puntos de interés primero
        hideAllPointsOfInterest(poiContainer)

        when (sectionTitle) {
            "Cabeza" -> {
                setupHeadPointsOfInterest(poiContainer)
            }
            "Torso" -> {
                setupTorsoPointsOfInterest(poiContainer)
            }
            "Piernas" -> {
                setupLegsPointsOfInterest(poiContainer)
            }
        }
    }

    private fun hideAllPointsOfInterest(container: View) {
        container.findViewById<View>(R.id.poi_eyes).visibility = View.GONE
        container.findViewById<View>(R.id.poi_mouth).visibility = View.GONE
        container.findViewById<View>(R.id.poi_brain).visibility = View.GONE
        container.findViewById<View>(R.id.poi_heart).visibility = View.GONE
        container.findViewById<View>(R.id.poi_lungs).visibility = View.GONE
        container.findViewById<View>(R.id.poi_stomach).visibility = View.GONE
        container.findViewById<View>(R.id.poi_knee).visibility = View.GONE
        container.findViewById<View>(R.id.poi_thigh).visibility = View.GONE
        container.findViewById<View>(R.id.poi_foot).visibility = View.GONE
    }

    private fun setupHeadPointsOfInterest(container: View) {
        val eyesPoint = container.findViewById<View>(R.id.poi_eyes)
        val mouthPoint = container.findViewById<View>(R.id.poi_mouth)
        val brainPoint = container.findViewById<View>(R.id.poi_brain)

        eyesPoint.visibility = View.VISIBLE
        mouthPoint.visibility = View.VISIBLE
        brainPoint.visibility = View.VISIBLE

        eyesPoint.setOnClickListener {
            navigateToDetailView("Ojos", "Los ojos son órganos sensoriales que permiten la visión.",
                R.drawable.eyes_detail, it)
        }

        mouthPoint.setOnClickListener {
            navigateToDetailView("Boca", "La boca permite ingerir alimentos y comunicación verbal.",
                R.drawable.mouth_detail, it)
        }

        brainPoint.setOnClickListener {
            navigateToDetailView("Cerebro", "El cerebro es el centro de control del sistema nervioso.",
                R.drawable.brain_detail, it)
        }
    }

    private fun setupTorsoPointsOfInterest(container: View) {
        val heartPoint = container.findViewById<View>(R.id.poi_heart)
        val lungsPoint = container.findViewById<View>(R.id.poi_lungs)
        val stomachPoint = container.findViewById<View>(R.id.poi_stomach)

        heartPoint.visibility = View.VISIBLE
        lungsPoint.visibility = View.VISIBLE
        stomachPoint.visibility = View.VISIBLE

        heartPoint.setOnClickListener {
            navigateToDetailView("Corazón", "El corazón bombea sangre a través del sistema circulatorio.",
                R.drawable.heart_detail, it)
        }

        lungsPoint.setOnClickListener {
            navigateToDetailView("Pulmones", "Los pulmones son responsables del intercambio de gases en la respiración.",
                R.drawable.lungs_detail, it)
        }

        stomachPoint.setOnClickListener {
            navigateToDetailView("Estómago", "El estómago es parte del sistema digestivo.",
                R.drawable.stomach_detail, it)
        }
    }

    private fun setupLegsPointsOfInterest(container: View) {
        val kneePoint = container.findViewById<View>(R.id.poi_knee)
        val thighPoint = container.findViewById<View>(R.id.poi_thigh)
        val footPoint = container.findViewById<View>(R.id.poi_foot)

        kneePoint.visibility = View.VISIBLE
        thighPoint.visibility = View.VISIBLE
        footPoint.visibility = View.VISIBLE

        kneePoint.setOnClickListener {
            navigateToDetailView("Rodilla", "La rodilla es una articulación compleja que permite la flexión de la pierna.",
                R.drawable.knee_detail, it)
        }

        thighPoint.setOnClickListener {
            navigateToDetailView("Muslo", "El muslo contiene los músculos más grandes del cuerpo.",
                R.drawable.thigh_detail, it)
        }

        footPoint.setOnClickListener {
            navigateToDetailView("Pie", "El pie es la base de apoyo para la bipedación humana.",
                R.drawable.foot_detail, it)
        }
    }

    private fun navigateToDetailView(title: String, description: String, imageResId: Int, view: View) {
        val intent = Intent(this, BodyPartDetailActivity::class.java).apply {
            putExtra("PART_TITLE", title)
            putExtra("PART_DESCRIPTION", description)
            putExtra("PART_IMAGE", imageResId)
        }

        // Crear una transición animada
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            view,
            "shared_part_transition"
        )

        startActivity(intent, options.toBundle())
    }
}