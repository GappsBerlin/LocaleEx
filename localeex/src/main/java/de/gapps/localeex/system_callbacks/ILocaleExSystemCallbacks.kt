package de.gapps.localeex.system_callbacks

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Configuration

interface ILocaleExSystemCallbacks {

    fun Activity.activityAttachBaseContext(newBase: Context): Context
    fun Activity.activityOnCreate()
    fun Activity.activityOnConfigurationChanged(newConfig: Configuration): Configuration
    fun Activity.activityApplyOverrideConfiguration(overrideConfiguration: Configuration): Configuration
    fun Activity.activityOnDestroy()

    fun Application.applicationAttachBaseContext(newBase: Context): Context
    fun Application.applicationOnConfigurationChanged(newConfig: Configuration): Configuration
}