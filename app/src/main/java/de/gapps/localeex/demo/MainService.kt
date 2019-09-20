package de.gapps.localeex.demo

import android.content.Intent
import android.os.Binder
import de.gapps.localeex.impl.LocaleExService

class MainService : LocaleExService() {

    inner class LocalBinder : Binder() {
        internal val service: MainService
            get() = this@MainService
    }

    private val binder = LocalBinder()

    override fun onBind(intent: Intent?) = binder
}