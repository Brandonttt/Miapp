package com.example.capas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val themeManager = ThemeManager.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. Aplicar tema antes de setContentView
        themeManager.applyTheme(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        setupThemeSwitch()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.info_container,
                    BodyInfoFragment.newInstance(
                        "Sistema del Cuerpo Humano",
                        "Toca una región del cuerpo para explorar sus funciones."
                    ),
                    "INFO_MAIN"
                )
                .commit()
        }

        setupClickableRegions()
    }

    private fun setupThemeSwitch() {
        val sw = findViewById<Switch>(R.id.theme_switch)
        sw.setOnCheckedChangeListener(null)
        sw.isChecked = themeManager.isDarkModeEnabled(this)
        sw.setOnCheckedChangeListener { _, checked ->
            val changed = themeManager.setDarkModeEnabled(this, checked)
            if (changed) recreate() // Reinflar solo si cambió
        }
    }

    private fun setupClickableRegions() {
        findViewById<View>(R.id.head_region).setOnClickListener {
            navigate("Cabeza", "Centro de control del cuerpo.", R.drawable.head_detail, it)
        }
        findViewById<View>(R.id.torso_region).setOnClickListener {
            navigate("Torso", "Contiene órganos vitales.", R.drawable.torso_detail, it)
        }
        findViewById<View>(R.id.legs_region).setOnClickListener {
            navigate("Piernas", "Soporte y locomoción.", R.drawable.legs_detail, it)
        }
    }

    private fun navigate(title: String, desc: String, img: Int, sharedView: View) {
        val intent = Intent(this, BodySectionActivity::class.java).apply {
            putExtra("SECTION_TITLE", title)
            putExtra("SECTION_DESCRIPTION", desc)
            putExtra("SECTION_IMAGE", img)
        }
        val opts = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this, sharedView, "shared_body_transition"
        )
        startActivity(intent, opts.toBundle())
    }
}