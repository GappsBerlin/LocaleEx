package de.gapps.localeex.preferences

import android.content.Context
import de.gapps.localeex.preferences.LocaleExPreferences.PREFS_KEY
import de.gapps.localeex.preferences.delegates.FilePreferenceProperty
import de.gapps.localeex.preferences.delegates.SharedPreferenceProperty

internal object LocaleExPreferencesInternal :
    ILocaleExPreferencesInternal {

    private const val PREFS_RECREATE_ACTIVITY_KEY = "PREFS_RECREATE_ACTIVITY_KEY"
    private const val PREFS_RESTART_ACTIVITY_KEY = "PREFS_RESTART_ACTIVITY_KEY"
    private const val PREFS_RESTART_APPLICATION_KEY = "PREFS_RESTART_APPLICATION_KEY"
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


    override var Context.shouldRecreateActivity: Boolean by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_RECREATE_ACTIVITY_KEY,
        false
    )

    override var Context.shouldRestartActivity: Boolean by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_RESTART_ACTIVITY_KEY,
        false
    )

    override var Context.shouldRestartApplication: Boolean by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_RESTART_APPLICATION_KEY,
        true
    )

    override var Context.handleDeprecationInRestore: Boolean by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_HANDLE_DEPRECATION_RESTORE,
        false
    )

    override var Context.handleDeprecationInApply: Boolean by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_HANDLE_DEPRECATION_APPLY,
        false
    )

    override var Context.handleDeprecationInUpdateConfig: Boolean by SharedPreferenceProperty(
        PREFS_KEY,
        PREFS_HANDLE_DEPRECATION_UPDATE_CONFIG,
        false
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
}