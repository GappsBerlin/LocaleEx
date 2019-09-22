package de.gapps.localeex

import android.content.Context
import android.content.SharedPreferences
import java.util.*


internal class LocaleExPreferences(private val context: Context) : ISharedPreferenceHolder {

    companion object {

        private const val PREFS_KEY = "PREFS_KEY"
        private const val PREFS_LANGUAGE_KEY = "PREFS_LANGUAGE_KEY"
        private const val PREFS_COUNTRY_KEY = "PREFS_COUNTRY_KEY"
        private const val PREFS_VARIANT_KEY = "PREFS_VARIANT_KEY"
    }

    override val sharedPrefs: SharedPreferences
        get() = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)

    private var languagePref: String by PreferenceProperty(
        PREFS_LANGUAGE_KEY,
        Locale.getDefault().language
    )
    private var countryPref: String by PreferenceProperty(
        PREFS_COUNTRY_KEY,
        Locale.getDefault().country
    )
    private var variantPref: String by PreferenceProperty(
        PREFS_VARIANT_KEY,
        Locale.getDefault().variant
    )

    var localePref: Locale
        get() = Locale(languagePref, countryPref, variantPref)
        set(value) {
            languagePref = value.language
            countryPref = value.country
            variantPref = value.variant
        }
}