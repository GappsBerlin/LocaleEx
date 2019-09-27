package de.gapps.localeex.demo

import android.app.Service
import android.content.Intent
import android.os.Binder

class MainService : Service() {

    inner class LocalBinder : Binder() {
        internal val service: MainService
            get() = this@MainService
    }

    private val binder = LocalBinder()

    override fun onBind(intent: Intent?) = binder
}