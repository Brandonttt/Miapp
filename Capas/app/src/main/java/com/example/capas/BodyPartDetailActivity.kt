package com.example.capas

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BodyPartDetailActivity : AppCompatActivity() {

    private val themeManager = ThemeManager.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_body_part_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Aplicar tema inicial
        applyCurrentTheme()

        // Recibir datos del intent
        val partTitle = intent.getStringExtra("PART_TITLE") ?: "Parte del Cuerpo"
        val partDescription = intent.getStringExtra("PART_DESCRIPTION") ?: ""
        val partImageId = intent.getIntExtra("PART_IMAGE", R.drawable.body_default)

        // Configurar vista
        val titleView = findViewById<TextView>(R.id.detail_title)
        titleView.text = partTitle

        val descriptionView = findViewById<TextView>(R.id.detail_description)
        descriptionView.text = partDescription

        val imageView = findViewById<ImageView>(R.id.detail_image)
        imageView.setImageResource(partImageId)

        // Aplicar animaciones
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        imageView.startAnimation(fadeIn)

        // Agregar fragmento de información detallada
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_info_container,
                    BodyDetailInfoFragment.newInstance(partTitle, partDescription, partImageId))
                .commit()
        }

        // Configurar botones
        setupButtons()
    }

    private fun applyCurrentTheme() {
        val rootView = findViewById<View>(R.id.detail_container)
        themeManager.applyThemeToView(this, rootView)
    }

    private fun setupButtons() {
        val backButton = findViewById<Button>(R.id.back_to_section_button)
        val shareButton = findViewById<Button>(R.id.share_button)

        backButton?.setOnClickListener {
            finish()
        }

        shareButton?.setOnClickListener {
            // Implementar funcionalidad de compartir
        }
    }
}