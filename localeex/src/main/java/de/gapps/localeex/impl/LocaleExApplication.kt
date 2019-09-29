package de.gapps.localeex.impl

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import de.gapps.localeex.ILocaleEx
import de.gapps.localeex.LocaleEx

open class LocaleExApplication : Application(), ILocaleEx by LocaleEx {

    override fun attachBaseContext(newBase: Context) =
        super.attachBaseContext(applicationAttachBaseContext(newBase))

    override fun onConfigurationChanged(newConfig: Configuration) =
        super.onConfigurationChanged(applicationOnConfigurationChanged(newConfig))
}