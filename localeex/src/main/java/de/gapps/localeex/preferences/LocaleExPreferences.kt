package de.gapps.localeex.preferences

import android.annotation.SuppressLint
import android.content.Context
import de.gapps.localeex.preferences.ILocaleExPreferences.DeprecationHandling
import de.gapps.localeex.preferences.delegates.FilePreferenceProperty
import de.gapps.localeex.preferences.delegates.SharedPreferenceProperty
import java.util.*

@SuppressLint("ConstantLocale")
internal object LocaleExPreferences : ILocaleExPreferences {

    private const val PREFS_KEY = "PREFS_KEY"

    private const val PREFS_LANGUAGE_KEY = "PREFS_LANGUAGE_KEY"
    private const val PREFS_COUNTRY_KEY = "PREFS_COUNTRY_KEY"
    private const val PREFS_VARIANT_KEY = "PREFS_VARIANT_KEY"
    private const val PREFS_HANDLE_DEPRECATION_RESTORE = "PREFS_HANDLE_DEPRECATION_RESTORE"
    private const val PREFS_HANDLE_DEPRECATION_APPLY = "PREFS_HANDLE_DEPRECATION_APPLY"
    private const val PREFS_HANDLE_DEPRECATION_UPDATE_CONFIG =
        "PREFS_HANDLE_DEPRECATION_UPDATE_CONFIG"
    private const val PREFS_RESTORE_BASE_CONTEXT_ACTIVITY = "PREFS_RESTORE_BASE_CONTEXT_ACTIVITY"
    private const val PREFS_RESTORE_BASE_CONTEXT_APPLICATION =
        "PREFS_RESTORE_BASE_CONTEXT_APPLICATION"
    private const val PREFS_MODIFY_APPLY_OVERRIDE_CONFIGURATION =
        "PREFS_MODIFY_APPLY_OVERRIDE_CONFIGURATION"
    private const val PREFS_RESTORE_CONFIG_CHANGED_ACTIVITY =
        "PREFS_RESTORE_CONFIG_CHANGED_ACTIVITY"
    private const val PREFS_RESTORE_CONFIG_CHANGED_APPLICATION =
        "PREFS_RESTORE_CONFIG_CHANGED_APPLICATION"
    private const val PREFS_APPLY_LOCALE_TO_APPLICATION_ON_CHANGE =
        "PREFS_APPLY_LOCALE_TO_APPLICATION_ON_CHANGE"


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


    override var postAction: ILocaleExPreferences.PostAction =
        ILocaleExPreferences.PostAction.RestartApplication()

    override var Context.handleDeprecationInRestore: DeprecationHandling by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_HANDLE_DEPRECATION_RESTORE,
        DeprecationHandling.IGNORE,
        setter = { it.toString() },
        getter = { DeprecationHandling.valueOf(it) }
    )

    override var Context.handleDeprecationInApply: DeprecationHandling by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_HANDLE_DEPRECATION_APPLY,
        DeprecationHandling.IGNORE,
        setter = { it.toString() },
        getter = { DeprecationHandling.valueOf(it) }
    )

    override var Context.handleDeprecationInUpdateConfig: DeprecationHandling by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_HANDLE_DEPRECATION_UPDATE_CONFIG,
        DeprecationHandling.IGNORE,
        setter = { it.toString() },
        getter = { DeprecationHandling.valueOf(it) }
    )

    override var Context.restoreInApplyOverrideConfiguration: Boolean by FilePreferenceProperty(
        PREFS_MODIFY_APPLY_OVERRIDE_CONFIGURATION,
        true
    )

    override var Context.restoreInConfigChangedOfActivity: Boolean by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_RESTORE_CONFIG_CHANGED_ACTIVITY,
        true
    )

    override var Context.restoreInConfigChangedOfApplication: Boolean by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_RESTORE_CONFIG_CHANGED_APPLICATION,
        true
    )

    override var Context.restoreInBaseContextOfActivity: Boolean by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_RESTORE_BASE_CONTEXT_ACTIVITY,
        true
    )

    override var Context.restoreInBaseContextOfApplication: Boolean by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_RESTORE_BASE_CONTEXT_APPLICATION,
        true
    )

    override var Context.applyLocaleToApplicationOnChange: Boolean by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_APPLY_LOCALE_TO_APPLICATION_ON_CHANGE,
        true
    )
}

