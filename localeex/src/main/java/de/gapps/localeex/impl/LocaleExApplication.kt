package de.gapps.localeex.impl

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import de.gapps.localeex.ILocaleEx
import de.gapps.localeex.LocaleEx

open class LocaleExApplication : Application(), ILocaleEx by LocaleEx {

    override fun attachBaseContext(base: Context) = super.attachBaseContext(base.restoreLocale())

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        restoreLocale()
    }
}