package de.gapps.localeex

import android.content.Context
import androidx.core.content.edit
import java.util.*


internal interface ILocaleExPrefs {

    companion object {

        private const val PREFS_KEY = "PREFS_KEY"
        private const val PREFS_LANGUAGE_KEY = "PREFS_LANGUAGE_KEY"
        private const val PREFS_COUNTRY_KEY = "PREFS_COUNTRY_KEY"
        private const val PREFS_VARIANT_KEY = "PREFS_VARIANT_KEY"
    }

    private val Context.sharedPrefs
        get() = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)

    private var Context.languagePref: String
        get() = sharedPrefs.getString(PREFS_LANGUAGE_KEY, null) ?: Locale.getDefault().language
        set(value) = sharedPrefs.edit { putString(PREFS_LANGUAGE_KEY, value) }

    private var Context.countryPref: String
        get() = sharedPrefs.getString(PREFS_COUNTRY_KEY, null) ?: Locale.getDefault().country
        set(value) = sharedPrefs.edit { putString(PREFS_COUNTRY_KEY, value) }

    private var Context.variantPref: String
        get() = sharedPrefs.getString(PREFS_VARIANT_KEY, null) ?: Locale.getDefault().variant
        set(value) = sharedPrefs.edit { putString(PREFS_VARIANT_KEY, value) }

    var Context.localePref: Locale
        get() = Locale(languagePref, countryPref, variantPref)
        set(value) {
            languagePref = value.language
            countryPref = value.country
            variantPref = value.variant
        }
}