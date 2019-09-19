@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package de.gapps.localeex

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import java.util.*


object LocaleEx : ILocaleEx, ILocaleExPrefs, ILocaleExListenerHandler by LocaleExListenerHandler {

    private const val TAG = "LocaleEx"

    override fun Context.restoreLocale() =
        updateResources(localePref, true)

    override fun Context.applyNewLocale(locale: Locale) =
        updateResources(locale, false)

    override fun Context.updateConfiguration(config: Configuration): Configuration =
        updateConfigurationInternal(config, handleDeprecation = true).second

    private fun Context.updateResources(locale: Locale, handleDeprecation: Boolean): Context = run {
        localePref = locale
        Locale.setDefault(locale)

        val config = Configuration(resources.configuration)
        updateConfigurationInternal(config, locale, handleDeprecation).first
    }

    private fun Context.updateConfigurationInternal(
        config: Configuration,
        locale: Locale = localePref,
        handleDeprecation: Boolean
    ): Pair<Context, Configuration> {
        Log.v(
            TAG, "updateConfigurationInternal() locale: $locale (localePref=$localePref); " +
                    "config: $config; "
        )

        return (if (handleDeprecation && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale)
            Pair(createConfigurationContext(config), config)
        } else {
            @Suppress("DEPRECATION")
            config.locale = locale
            @Suppress("DEPRECATION")
            resources.updateConfiguration(config, resources.displayMetrics)
            Pair(this, config)
        }).also { LocaleExListenerHandler.notifyListener(it.first) }
    }
}