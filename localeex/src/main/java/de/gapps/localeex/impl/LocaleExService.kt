package de.gapps.localeex.impl

import android.app.Service
import android.content.Context
import de.gapps.localeex.ILocaleEx
import de.gapps.localeex.LocaleEx

abstract class LocaleExService : Service(), ILocaleEx by LocaleEx {
    override fun attachBaseContext(base: Context) =
        super.attachBaseContext(base.restoreLocale())
}