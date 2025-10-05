package com.example.capas

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import android.util.Log

class ThemeManager private constructor() {

    companion object {
        private const val PREFS_NAME = "theme_preferences"
        private const val KEY_IS_DARK_MODE = "is_dark_mode_enabled"
        private const val TAG = "ThemeManager"

        @Volatile
        private var INSTANCE: ThemeManager? = null

        fun getInstance(): ThemeManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ThemeManager().also { INSTANCE = it }
            }
        }
    }

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Verifica si el modo oscuro está habilitado
     */
    fun isDarkModeEnabled(context: Context): Boolean {
        val isDark = getSharedPreferences(context).getBoolean(KEY_IS_DARK_MODE, false)
        Log.d(TAG, "isDarkModeEnabled: $isDark")
        return isDark
    }

    /**
     * Guarda la preferencia de modo oscuro y aplica el tema inmediatamente
     */
    fun setDarkModeEnabled(context: Context, enabled: Boolean) {
        Log.d(TAG, "setDarkModeEnabled: $enabled")

        // Guardar la preferencia
        getSharedPreferences(context)
            .edit()
            .putBoolean(KEY_IS_DARK_MODE, enabled)
            .commit()

        // Aplicar el tema inmediatamente usando AppCompatDelegate
        applyThemeGlobally(enabled)
    }

    /**
     * Aplica el tema globalmente usando AppCompatDelegate
     */
    private fun applyThemeGlobally(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            Log.d(TAG, "Applied DARK theme globally")
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Log.d(TAG, "Applied LIGHT theme globally")
        }
    }

    /**
     * Inicializa el tema al arrancar la aplicación
     */
    fun initializeTheme(context: Context) {
        val isDarkMode = isDarkModeEnabled(context)
        Log.d(TAG, "initializeTheme: $isDarkMode")
        applyThemeGlobally(isDarkMode)
    }

    /**
     * Cambia el tema (para uso con el switch)
     */
    fun toggleTheme(context: Context, newState: Boolean) {
        Log.d(TAG, "toggleTheme to: $newState")
        setDarkModeEnabled(context, newState)
    }
}