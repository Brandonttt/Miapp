package com.example.capas

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.TextView
import android.widget.LinearLayout
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import android.util.Log

class ThemeManager private constructor() {

    companion object {
        private const val PREFS_NAME = "theme_prefs"
        private const val KEY_IS_DARK_MODE = "is_dark_mode"
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

    fun isDarkModeEnabled(context: Context): Boolean {
        val isDark = getSharedPreferences(context).getBoolean(KEY_IS_DARK_MODE, false)
        Log.d(TAG, "isDarkModeEnabled: $isDark")
        return isDark
    }

    fun setDarkModeEnabled(context: Context, enabled: Boolean) {
        Log.d(TAG, "setDarkModeEnabled: $enabled")
        getSharedPreferences(context)
            .edit()
            .putBoolean(KEY_IS_DARK_MODE, enabled)
            .apply()
    }

    fun applyTheme(context: Context) {
        // Para compatibilidad - no hace nada
    }

    fun applyThemeToView(context: Context, rootView: View) {
        val isDarkMode = isDarkModeEnabled(context)
        Log.d(TAG, "applyThemeToView: isDarkMode = $isDarkMode")
        applyThemeRecursively(context, rootView, isDarkMode)
    }

    private fun applyThemeRecursively(context: Context, view: View, isDarkMode: Boolean) {
        when (view) {
            is ConstraintLayout -> {
                if (view.id == R.id.main || view.id == R.id.section_container || view.id == R.id.detail_container) {
                    val backgroundColor = if (isDarkMode) {
                        ContextCompat.getColor(context, R.color.background_dark)
                    } else {
                        ContextCompat.getColor(context, R.color.background_light)
                    }
                    Log.d(TAG, "Setting background for ${getViewName(view.id)} to ${if (isDarkMode) "dark" else "light"}")
                    view.setBackgroundColor(backgroundColor)
                }
            }

            is CardView -> {
                if (view.id == R.id.main_card || view.id == R.id.body_container || view.id == R.id.info_container) {
                    val cardColor = if (isDarkMode) {
                        ContextCompat.getColor(context, R.color.card_background_dark)
                    } else {
                        ContextCompat.getColor(context, R.color.card_background_light)
                    }
                    Log.d(TAG, "Setting card background for ${getViewName(view.id)} to ${if (isDarkMode) "dark" else "light"}")
                    view.setCardBackgroundColor(cardColor)
                }
            }

            is TextView -> {
                if (view.id != R.id.section_title &&
                    view.id != R.id.main_title &&
                    view.id != R.id.subtitle &&
                    view.id != R.id.detail_title) {

                    val textColor = if (isDarkMode) {
                        ContextCompat.getColor(context, R.color.text_primary_dark)
                    } else {
                        ContextCompat.getColor(context, R.color.text_primary_light)
                    }
                    view.setTextColor(textColor)
                }
            }

            is LinearLayout, is FrameLayout -> {
                if (view.background != null) {
                    val backgroundColor = if (isDarkMode) {
                        ContextCompat.getColor(context, R.color.surface_dark)
                    } else {
                        ContextCompat.getColor(context, R.color.surface_light)
                    }
                    view.setBackgroundColor(backgroundColor)
                }
            }
        }

        // Aplicar recursivamente a vistas hijas
        if (view is android.view.ViewGroup) {
            for (i in 0 until view.childCount) {
                applyThemeRecursively(context, view.getChildAt(i), isDarkMode)
            }
        }
    }

    private fun getViewName(id: Int): String {
        return when (id) {
            R.id.main -> "main"
            R.id.section_container -> "section_container"
            R.id.detail_container -> "detail_container"
            R.id.main_card -> "main_card"
            R.id.body_container -> "body_container"
            R.id.info_container -> "info_container"
            else -> "unknown_$id"
        }
    }
}