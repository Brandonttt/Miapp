package com.example.capas

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Inicializar el tema al arrancar la aplicación
        val themeManager = ThemeManager.getInstance()
        themeManager.initializeTheme(this)
    }
}