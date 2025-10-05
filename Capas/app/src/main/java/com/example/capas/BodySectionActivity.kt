package com.example.capas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BodySectionActivity : AppCompatActivity() {

    private val themeManager = ThemeManager.getInstance()
    private lateinit var sectionTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        // Aplicar tema aquí también
        themeManager.applyTheme(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_body_section)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.section_container)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        sectionTitle = intent.getStringExtra("SECTION_TITLE") ?: "Sección"
        val desc = intent.getStringExtra("SECTION_DESCRIPTION") ?: ""
        val imgRes = intent.getIntExtra("SECTION_IMAGE", R.drawable.body_default)

        findViewById<TextView>(R.id.section_title).text = sectionTitle
        findViewById<ImageView>(R.id.section_image).apply {
            setImageResource(imgRes)
            startAnimation(AnimationUtils.loadAnimation(this@BodySectionActivity, R.anim.fade_in))
        }

        setupThemeSwitch()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.section_info_container,
                    BodyInfoFragment.newInstance(sectionTitle, desc),
                    "INFO_SECTION"
                )
                .commit()
        }

        setupPointsOfInterest()
    }

    private fun setupThemeSwitch() {
        val sw = findViewById<Switch>(R.id.theme_switch)
        sw.setOnCheckedChangeListener(null)
        sw.isChecked = themeManager.isDarkModeEnabled(this)
        sw.setOnCheckedChangeListener { _, checked ->
            val changed = themeManager.setDarkModeEnabled(this, checked)
            if (changed) recreate()
        }
    }

    private fun setupPointsOfInterest() {
        val p = findViewById<View>(R.id.points_of_interest_container)
        when (sectionTitle) {
            "Cabeza" -> head(p)
            "Torso" -> torso(p)
            "Piernas" -> legs(p)
        }
    }

    private fun head(p: View) {
        p.findViewById<View>(R.id.poi_eyes)?.activate("Ojos", "Órganos de la visión.", R.drawable.eyes)
        p.findViewById<View>(R.id.poi_mouth)?.activate("Boca", "Inicio del sistema digestivo.", R.drawable.mouth)
        p.findViewById<View>(R.id.poi_brain)?.activate("Cerebro", "Centro de control nervioso.", R.drawable.brain)
    }

    private fun torso(p: View) {
        p.findViewById<View>(R.id.poi_heart)?.activate("Corazón", "Bombea sangre.", R.drawable.heart)
        p.findViewById<View>(R.id.poi_lungs)?.activate("Pulmones", "Intercambio gaseoso.", R.drawable.lungs)
        p.findViewById<View>(R.id.poi_stomach)?.activate("Estómago", "Digestión inicial.", R.drawable.stomach)
    }

    private fun legs(p: View) {
        p.findViewById<View>(R.id.poi_knee)?.activate("Rodilla", "Articulación compleja.", R.drawable.knee)
        p.findViewById<View>(R.id.poi_thigh)?.activate("Muslo", "Músculos potentes.", R.drawable.thigh)
        p.findViewById<View>(R.id.poi_foot)?.activate("Pie", "Soporte y locomoción.", R.drawable.foot)
    }

    private fun View.activate(title: String, desc: String, img: Int) {
        visibility = View.VISIBLE
        setOnClickListener {
            openDetail(title, desc, img, it)
        }
    }

    private fun openDetail(title: String, desc: String, img: Int, shared: View) {
        val intent = Intent(this, BodyPartDetailActivity::class.java).apply {
            putExtra("PART_TITLE", title)
            putExtra("PART_DESCRIPTION", desc)
            putExtra("PART_IMAGE", img)
        }
        val opts = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this, shared, "shared_part_transition"
        )
        startActivity(intent, opts.toBundle())
    }
}