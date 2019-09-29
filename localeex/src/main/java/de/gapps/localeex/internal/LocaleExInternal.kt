package de.gapps.localeex.internal

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import de.gapps.localeex.LocaleEx
import de.gapps.localeex.listener.LocaleExListenerHandler
import de.gapps.localeex.preferences.ILocaleExPreferences
import de.gapps.localeex.preferences.LocaleExPreferences
import java.util.*

internal object LocaleExInternal : ILocaleExInternal,
    ILocaleExPreferences by LocaleExPreferences {

    private val TAG = LocaleEx::class.java.simpleName

    override fun Context.restoreLocale(): Context {
        val locale = storedLocale
        val newContext = updateResources(locale, handleDeprecationInRestore)
        Log.d(TAG, "restoreLocale() locale=$locale; context=$newContext")
        return newContext
    }

    override fun Context.applyLocale(locale: Locale): Context {
        val newContext = updateResources(locale, handleDeprecationInApply)
        Log.d(TAG, "applyLocale() locale=$locale")
        LocaleExListenerHandler.notifyListener(newContext)
        return newContext
    }

    override fun Context.updateConfiguration(config: Configuration): Configuration {
        val newConfig = updateConfigurationInternal(
            config,
            handleDeprecation = handleDeprecationInUpdateConfig
        ).second
        Log.d(TAG, "updateConfiguration() newConfig=$config; oldConfig=$config")
        return newConfig
    }

    private fun Context.updateResources(locale: Locale, handleDeprecation: Boolean): Context = run {
        storedLocale = locale
        Locale.setDefault(locale)

        val config = Configuration(resources.configuration)
        updateConfigurationInternal(config, locale, handleDeprecation).first
    }

    private fun Context.updateConfigurationInternal(
        config: Configuration,
        locale: Locale = storedLocale,
        handleDeprecation: Boolean
    ) = try {
        if (handleDeprecation && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale)
            Pair(createConfigurationContext(config), config)
        } else {
            @Suppress("DEPRECATION")
            config.locale = locale
            @Suppress("DEPRECATION")
            resources.updateConfiguration(config, resources.displayMetrics)
            Pair(this, config)
        }
    } catch (t: Throwable) {
        Pair(this, config)
    }.also {
        Log.v(TAG, "updateConfigurationInternal() locale: $locale; config: $config")
    }

}