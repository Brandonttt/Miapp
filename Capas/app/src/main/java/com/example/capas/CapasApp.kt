package com.example.capas

import android.app.Application

class CapasApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Ya no llamamos initialize(); cada Activity aplica el tema con themeManager.applyTheme()
    }
}