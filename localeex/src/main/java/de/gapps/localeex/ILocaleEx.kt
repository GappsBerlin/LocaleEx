package de.gapps.localeex

import android.content.Context
import android.content.res.Configuration
import java.util.*

/**
 *
 */
interface ILocaleEx : ILocaleExListenerHandler {

    /**
     * Returns the locale that was set with [applyLocale].
     */
    val Context.storedLocale: Locale

    /**
     * Restores the last [Locale] which was set with [applyLocale] to the [Context].
     *
     * @return [Context] with the restored [Locale]
     */
    fun Context.restoreLocale(): Context

    /**
     * Applies the provided [Locale] to the [Context].
     *
     * @return [Context] with the applied [Locale]
     */
    fun Context.applyLocale(locale: Locale): Context

    /**
     * Updates the [Context] with the provided [Configuration].
     *
     * @return Updated [Configuration]
     */
    fun Context.updateConfiguration(config: Configuration): Configuration
}