package de.gapps.localeex.internal

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import de.gapps.localeex.LocaleEx.TAG
import de.gapps.localeex.listener.LocaleExListenerHandler
import de.gapps.localeex.preferences.ILocaleExPreferences
import de.gapps.localeex.preferences.ILocaleExPreferences.DeprecationHandling
import de.gapps.localeex.preferences.LocaleExPreferences
import java.util.*

internal object LocaleExInternal : ILocaleExInternal,
    ILocaleExPreferences by LocaleExPreferences {

    override fun Context.restoreLocale(): Context {
        val newContext = updateResources(storedLocale, handleDeprecationInRestore)
        Log.v(TAG, "restoreLocale() locale=$storedLocale; context=$newContext")
        return newContext
    }

    override fun Context.applyLocale(locale: Locale): Context {
        Log.v(TAG, "applyLocale() locale=$locale")
        val newContext = updateResources(locale, handleDeprecationInApply)
        if (applyLocaleToApplicationOnChange) newContext.applicationContext.restoreLocale()
        LocaleExListenerHandler.notifyListener(newContext)
        return newContext
    }

    override fun Context.updateConfiguration(config: Configuration): Configuration {
        val (_, newConfig) = updateConfigurationInternal(
            config,
            handleDeprecation = handleDeprecationInUpdateConfig
        )
        Log.v(TAG, "updateConfiguration() newConfig=$config; oldConfig=$config")
        return newConfig
    }

    private fun Context.updateResources(
        locale: Locale,
        handleDeprecation: DeprecationHandling
    ): Context = run {
        LocaleExPreferences.run { storedLocale = locale }
        Locale.setDefault(locale)

        val config = Configuration(resources.configuration)
        updateConfigurationInternal(config, locale, handleDeprecation).first
    }

    private fun Context.updateConfigurationInternal(
        config: Configuration,
        locale: Locale = storedLocale,
        handleDeprecation: DeprecationHandling
    ) = try {
        if (handleDeprecation == DeprecationHandling.RESPECT
            && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
        ) update(config, locale)
        else deprecateUpdate(config, locale)
    } catch (t: Throwable) {
        this to config
    }.also {
        Log.v(TAG, "updateConfigurationInternal() locale: $locale; config: $config")
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun Context.update(
        config: Configuration,
        locale: Locale
    ): Pair<Context, Configuration> {
        config.setLocale(locale)
        return createConfigurationContext(config) to config
    }

    @Suppress("DEPRECATION")
    private fun Context.deprecateUpdate(
        config: Configuration,
        locale: Locale
    ): Pair<Context, Configuration> {
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
        return this to config
    }

}