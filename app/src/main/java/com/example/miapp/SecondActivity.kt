package com.example.miapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var tvDataReceived: TextView
    private lateinit var btnBackToMain: Button
    private lateinit var btnFinishActivity: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Inicializar vistas
        tvDataReceived = findViewById(R.id.tvDataReceived)
        btnBackToMain = findViewById(R.id.btnBackToMain)
        btnFinishActivity = findViewById(R.id.btnFinishActivity)

        setupViews()
        setupClickListeners()

        // Recibir datos del Activity anterior (si los hay)
        receiveDataFromPreviousActivity()
    }

    private fun setupViews() {
        // Configurar la toolbar si la tienes
        supportActionBar?.apply {
            title = "Segunda Activity"
            setDisplayHomeAsUpEnabled(true) // Mostrar botón de regreso
        }
    }

    private fun setupClickListeners() {
        // Botón para regresar al MainActivity
        btnBackToMain.setOnClickListener {
            goBackToMainActivity()
        }

        // Botón para finalizar este Activity
        btnFinishActivity.setOnClickListener {
            finish() // Cierra este Activity y regresa al anterior
        }
    }

    private fun receiveDataFromPreviousActivity() {
        // Recibir datos enviados desde el Activity anterior
        val nombreUsuario = intent.getStringExtra("NOMBRE_USUARIO")
        val numeroContador = intent.getIntExtra("CONTADOR", 0)
        val esVip = intent.getBooleanExtra("ES_VIP", false)

        // Mostrar los datos recibidos
        tvDataReceived.text = """
            Datos recibidos:
            Nombre: ${nombreUsuario ?: "No especificado"}
            Contador: $numeroContador
            Es VIP: $esVip
        """.trimIndent()
    }

    private fun goBackToMainActivity() {
        // Simplemente finalizar este Activity (regresa al anterior)
        finish()
    }

    // Manejar el botón de regreso de la ActionBar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}