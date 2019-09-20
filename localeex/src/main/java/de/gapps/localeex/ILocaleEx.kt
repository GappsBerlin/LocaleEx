package de.gapps.localeex

import android.content.Context
import android.content.res.Configuration
import java.util.*

interface ILocaleEx {

    val Context.storedLocale: Locale

    fun Context.restoreLocale(): Context
    fun Context.applyNewLocale(locale: Locale): Context
    fun Context.updateConfiguration(config: Configuration): Configuration
}