package de.gapps.localeex

import android.content.Context
import java.util.*

interface ILocaleExPreferences {

    var Context.storedLocale: Locale

    var Context.handleDeprecationInRestore: Boolean
    var Context.handleDeprecationInApply: Boolean
    var Context.handleDeprecationInUpdateConfig: Boolean

    var Context.shouldRecreateActivity: Boolean
}