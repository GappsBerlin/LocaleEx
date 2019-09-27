package de.gapps.localeex.preferences

import android.content.Context

interface ILocaleExPreferencesInternal {

    var Context.shouldRecreateActivity: Boolean
    var Context.shouldRestartActivity: Boolean
    var Context.shouldRestartApplication: Boolean

    var Context.handleDeprecationInRestore: Boolean
    var Context.handleDeprecationInApply: Boolean
    var Context.handleDeprecationInUpdateConfig: Boolean

    var Context.restoreInApplyOverrideConfiguration: Boolean
    var Context.restoreInConfigChangedOfActivity: Boolean
    var Context.restoreInConfigChangedOfApplication: Boolean
    var Context.restoreInBaseContextOfActivity: Boolean
    var Context.restoreInBaseContextOfApplication: Boolean

}