@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package de.gapps.localeex

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import java.util.*

/**
 * [LocaleEx] provides functionality to define an exclusive [Locale] in any [Context].
 *
 */
object LocaleEx : ILocaleEx,
    ILocaleExListenerHandler by LocaleExListenerHandler {

    private val TAG = LocaleEx::class.java.simpleName

    private val Context.prefs: LocaleExPreferences
        get() = LocaleExPreferences(this)

    override val Context.storedLocale: Locale
        get() = prefs.localePref

    override fun Context.restoreLocale() =
        updateResources(prefs.localePref, false)

    override fun Context.applyLocale(locale: Locale): Context {
        val newContext = updateResources(locale, false)
        LocaleExListenerHandler.notifyListener(newContext)
        return newContext
    }

    override fun Context.updateConfiguration(config: Configuration): Configuration =
        updateConfigurationInternal(config, handleDeprecation = true).second

    private fun Context.updateResources(locale: Locale, handleDeprecation: Boolean): Context = run {
        prefs.localePref = locale
        Locale.setDefault(locale)

        val config = Configuration(resources.configuration)
        updateConfigurationInternal(config, locale, handleDeprecation).first
    }

    private fun Context.updateConfigurationInternal(
        config: Configuration,
        locale: Locale = prefs.localePref,
        handleDeprecation: Boolean
    ): Pair<Context, Configuration> {
        Log.v(
            TAG,
            "updateConfigurationInternal() BEFORE locale: $locale (localePref=${prefs.localePref}); " +
                    "config: $config"
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
        }).also {
            Log.v(
                TAG,
                "updateConfigurationInternal() AFTER locale: $locale (localePref=${prefs.localePref}); " +
                        "config: $config"
            )
        }
    }
}