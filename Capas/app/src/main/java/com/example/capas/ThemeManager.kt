package com.example.capas

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

/**
 * ThemeManager: Aplica manualmente Theme.Capas.Light u .Dark
 * antes de setContentView() en cada Activity.
 */
class ThemeManager private constructor() {

    companion object {
        private const val PREFS = "theme_prefs_manual"
        private const val KEY_DARK = "dark_enabled"

        @Volatile private var INSTANCE: ThemeManager? = null

        fun getInstance(): ThemeManager =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ThemeManager().also { INSTANCE = it }
            }
    }

    private fun prefs(ctx: Context): SharedPreferences =
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    fun isDarkModeEnabled(ctx: Context): Boolean =
        prefs(ctx).getBoolean(KEY_DARK, false)

    /**
     * Debe llamarse ANTES de setContentView() en cada Activity.
     */
    fun applyTheme(activity: Activity) {
        val dark = isDarkModeEnabled(activity)
        activity.setTheme(
            if (dark) R.style.Theme_Capas_Dark
            else R.style.Theme_Capas_Light
        )
    }

    /**
     * Cambia la preferencia y devuelve true si cambió.
     */
    fun setDarkModeEnabled(ctx: Context, enable: Boolean): Boolean {
        val current = isDarkModeEnabled(ctx)
        if (current == enable) return false
        prefs(ctx).edit().putBoolean(KEY_DARK, enable).apply()
        return true
    }
}