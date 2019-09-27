package de.gapps.localeex.impl

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import de.gapps.localeex.ILocaleExInternal
import de.gapps.localeex.LocaleEx

open class LocaleExApplication : Application(), ILocaleExInternal by LocaleEx {

    override fun attachBaseContext(newBase: Context) =
        super.attachBaseContext(if (newBase.restoreInBaseContextOfApplication) newBase.restoreLocale() else newBase)

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (restoreInConfigChangedOfApplication) restoreLocale()
    }
}