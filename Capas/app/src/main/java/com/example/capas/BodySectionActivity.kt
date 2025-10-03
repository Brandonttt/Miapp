package com.example.capas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.animation.AnimationUtils

class BodySectionActivity : AppCompatActivity() {

    private lateinit var sectionTitle: String
    private val themeManager = ThemeManager.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_body_section)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.section_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Aplicar tema inicial
        applyCurrentTheme()

        // Recibir datos del intent
        sectionTitle = intent.getStringExtra("SECTION_TITLE") ?: "Sección del Cuerpo"
        val sectionDescription = intent.getStringExtra("SECTION_DESCRIPTION") ?: ""
        val sectionImageId = intent.getIntExtra("SECTION_IMAGE", R.drawable.body_default)

        // Configurar vista
        val titleView = findViewById<TextView>(R.id.section_title)
        titleView.text = sectionTitle

        val imageView = findViewById<ImageView>(R.id.section_image)
        imageView.setImageResource(sectionImageId)

        // Aplicar animación de fade in a la imagen
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        imageView.startAnimation(fadeIn)

        // Configurar el switch de tema
        setupThemeSwitch()

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

    private fun applyCurrentTheme() {
        val rootView = findViewById<View>(R.id.section_container)
        themeManager.applyThemeToView(this, rootView)
    }

    private fun setupThemeSwitch() {
        val themeSwitch = findViewById<Switch>(R.id.theme_switch)

        // Establecer estado inicial
        themeSwitch.setOnCheckedChangeListener(null)
        themeSwitch.isChecked = themeManager.isDarkModeEnabled(this)

        // Configurar listener
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Guardar preferencia
            themeManager.setDarkModeEnabled(this, isChecked)

            // Aplicar tema inmediatamente a esta vista
            applyCurrentTheme()
        }
    }

    private fun setupPointsOfInterest() {
        val poiContainer = findViewById<View>(R.id.points_of_interest_container)

        when (sectionTitle) {
            "Cabeza" -> setupHeadPointsOfInterest(poiContainer)
            "Torso" -> setupTorsoPointsOfInterest(poiContainer)
            "Piernas" -> setupLegsPointsOfInterest(poiContainer)
        }
    }

    private fun setupHeadPointsOfInterest(container: View) {
        val eyesPoint = container.findViewById<View>(R.id.poi_eyes)
        val mouthPoint = container.findViewById<View>(R.id.poi_mouth)
        val brainPoint = container.findViewById<View>(R.id.poi_brain)

        eyesPoint.visibility = View.VISIBLE
        mouthPoint.visibility = View.VISIBLE
        brainPoint.visibility = View.VISIBLE

        eyesPoint.setOnClickListener {
            navigateToDetailView("Ojos", "Órganos sensoriales responsables de la visión y percepción de luz.", R.drawable.eyes, it)
        }

        mouthPoint.setOnClickListener {
            navigateToDetailView("Boca", "Inicio del sistema digestivo y órgano clave para la comunicación verbal.", R.drawable.mouth, it)
        }

        brainPoint.setOnClickListener {
            navigateToDetailView("Cerebro", "Centro de control del sistema nervioso y órgano principal del pensamiento.", R.drawable.brain, it)
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
            navigateToDetailView("Corazón", "El corazón bombea sangre a todo el cuerpo a través del sistema circulatorio.", R.drawable.heart, it)
        }

        lungsPoint.setOnClickListener {
            navigateToDetailView("Pulmones", "Los pulmones son responsables del intercambio de oxígeno y dióxido de carbono.", R.drawable.lungs, it)
        }

        stomachPoint.setOnClickListener {
            navigateToDetailView("Estómago", "El estómago digiere los alimentos mediante ácidos y enzimas potentes.", R.drawable.stomach, it)
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
            navigateToDetailView("Rodilla", "La rodilla es una articulación compleja que conecta el muslo con la pierna.", R.drawable.knee, it)
        }

        thighPoint.setOnClickListener {
            navigateToDetailView("Muslo", "El muslo contiene algunos de los músculos más fuertes del cuerpo humano.", R.drawable.thigh, it)
        }

        footPoint.setOnClickListener {
            navigateToDetailView("Pie", "Los pies soportan el peso del cuerpo y son esenciales para la locomoción.", R.drawable.foot, it)
        }
    }

    private fun navigateToDetailView(partTitle: String, partDescription: String, imageResId: Int, view: View) {
        val intent = Intent(this, BodyPartDetailActivity::class.java).apply {
            putExtra("PART_TITLE", partTitle)
            putExtra("PART_DESCRIPTION", partDescription)
            putExtra("PART_IMAGE", imageResId)
        }

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "shared_part_transition")
        startActivity(intent, options.toBundle())
    }
}