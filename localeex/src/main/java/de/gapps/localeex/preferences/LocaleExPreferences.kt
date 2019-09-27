package de.gapps.localeex.preferences

import android.annotation.SuppressLint
import android.content.Context
import de.gapps.localeex.preferences.delegates.SharedPreferenceProperty
import java.util.*

@SuppressLint("ConstantLocale")
internal object LocaleExPreferences : ILocaleExPreferences {

    internal const val PREFS_KEY = "PREFS_KEY"
    private const val PREFS_LANGUAGE_KEY = "PREFS_LANGUAGE_KEY"
    private const val PREFS_COUNTRY_KEY = "PREFS_COUNTRY_KEY"
    private const val PREFS_VARIANT_KEY = "PREFS_VARIANT_KEY"

    private var Context.languagePref: String by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_LANGUAGE_KEY,
        Locale.getDefault().language,
        commit = true
    )

    private var Context.countryPref: String by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_COUNTRY_KEY,
        Locale.getDefault().country,
        commit = true
    )

    private var Context.variantPref: String by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_VARIANT_KEY,
        Locale.getDefault().variant,
        commit = true
    )

    override var Context.storedLocale: Locale
        get() = Locale(languagePref, countryPref, variantPref)
        set(value) {
            languagePref = value.language
            countryPref = value.country
            variantPref = value.variant
        }
}

