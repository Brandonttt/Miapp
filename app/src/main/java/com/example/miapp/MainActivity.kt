package com.example.miapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

        // Configurar el botón flotante para abrir la calculadora
        val fabCalculator = findViewById<FloatingActionButton>(R.id.fabCalculator)
        fabCalculator.setOnClickListener {
            openCalculator()
        }

        // Configurar el menú de navegación
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.textfields -> {
                    cargarFragment(TextFieldsFragment())
                    true
                }
                R.id.botones -> {
                    cargarFragment(BotonesFragment())
                    true
                }
                R.id.seleccion -> {
                    cargarFragment(SeleccionFragment())
                    true
                }
                R.id.listas -> {
                    cargarFragment(ListasFragment())
                    true
                }
                R.id.informacion -> {
                    cargarFragment(InformacionFragment())
                    true
                }
                else -> false
            }
        }

        // Cargar el primer fragment por defecto
        if (savedInstanceState == null) {
            cargarFragment(TextFieldsFragment())
            bottomNavigation.selectedItemId = R.id.textfields
        }
    }

    private fun cargarFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun openCalculator() {
        val intent = Intent(this, CalculatorActivity::class.java)
        startActivity(intent)
    }
}