package com.example.capas

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BodyPartDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_body_part_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recibir datos del intent
        val partTitle = intent.getStringExtra("PART_TITLE") ?: "Parte del Cuerpo"
        val partDescription = intent.getStringExtra("PART_DESCRIPTION") ?: ""
        val partImageId = intent.getIntExtra("PART_IMAGE", R.drawable.body_default)

        // Configurar vista
        val titleView = findViewById<TextView>(R.id.detail_title)
        val imageView = findViewById<ImageView>(R.id.detail_image)
        val descriptionView = findViewById<TextView>(R.id.detail_description)

        titleView.text = partTitle
        imageView.setImageResource(partImageId)
        descriptionView.text = partDescription

        // Aplicar animaciones
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)

        imageView.startAnimation(fadeIn)
        descriptionView.startAnimation(slideUp)

        // Agregar fragmento de información detallada
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_info_container,
                    BodyDetailInfoFragment.newInstance(partTitle))
                .commit()
        }
    }
}