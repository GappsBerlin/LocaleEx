package de.gapps.localeex

import android.annotation.SuppressLint
import android.content.Context
import java.util.*

@SuppressLint("ConstantLocale")
object LocaleExPreferences : ILocaleExPreferences {

    private const val PREFS_KEY = "PREFS_KEY"
    private const val PREFS_LANGUAGE_KEY = "PREFS_LANGUAGE_KEY"
    private const val PREFS_COUNTRY_KEY = "PREFS_COUNTRY_KEY"
    private const val PREFS_VARIANT_KEY = "PREFS_VARIANT_KEY"
    private const val PREFS_HANDLE_DEPRECATION_RESTORE = "PREFS_HANDLE_DEPRECATION_RESTORE"
    private const val PREFS_HANDLE_DEPRECATION_APPLY = "PREFS_HANDLE_DEPRECATION_APPLY"
    private const val PREFS_HANDLE_DEPRECATION_UPDATE_CONFIG =
        "PREFS_HANDLE_DEPRECATION_UPDATE_CONFIG"
    private const val PREFS_RECREATE_ACTIVITY_KEY = "PREFS_RECREATE_ACTIVITY_KEY"

    private var Context.languagePref: String by PreferenceProperty(
        PREFS_KEY,
        PREFS_LANGUAGE_KEY,
        Locale.getDefault().language
    )
    private var Context.countryPref: String by PreferenceProperty(
        PREFS_KEY,
        PREFS_COUNTRY_KEY,
        Locale.getDefault().country
    )
    private var Context.variantPref: String by PreferenceProperty(
        PREFS_KEY,
        PREFS_VARIANT_KEY,
        Locale.getDefault().variant
    )
    override var Context.storedLocale: Locale
        get() = Locale(languagePref, countryPref, variantPref)
        set(value) {
            languagePref = value.language
            countryPref = value.country
            variantPref = value.variant
        }

    override var Context.handleDeprecationInRestore: Boolean by PreferenceProperty(
        PREFS_KEY,
        PREFS_HANDLE_DEPRECATION_RESTORE,
        false
    )
    override var Context.handleDeprecationInApply: Boolean by PreferenceProperty(
        PREFS_KEY,
        PREFS_HANDLE_DEPRECATION_APPLY,
        false
    )
    override var Context.handleDeprecationInUpdateConfig: Boolean by PreferenceProperty(
        PREFS_KEY,
        PREFS_HANDLE_DEPRECATION_UPDATE_CONFIG,
        false
    )

    override var Context.shouldRecreateActivity: Boolean by PreferenceProperty(
        PREFS_KEY,
        PREFS_RECREATE_ACTIVITY_KEY,
        true
    )
}