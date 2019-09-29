package de.gapps.localeex.system_callbacks

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.util.Log
import de.gapps.localeex.internal.ILocaleExInternal
import de.gapps.localeex.internal.LocaleExInternal
import de.gapps.localeex.listener.ILocaleExListenerHandler
import de.gapps.localeex.listener.LocaleExListenerHandler
import java.lang.ref.WeakReference
import kotlin.system.exitProcess

@SuppressLint("StaticFieldLeak")
internal object LocaleExSystemCallbackHandler : ILocaleExSystemCallbacks,
    ILocaleExInternal by LocaleExInternal,
    ILocaleExListenerHandler by LocaleExListenerHandler {

    private val TAG = LocaleExSystemCallbackHandler::class.java.simpleName

    internal var usedActivity: WeakReference<Activity>? = null
        private set

    private fun Activity.startActivity() {
        val clazz = this::class.java
        Log.d(TAG, "startActivity() class=$clazz")
        val i = Intent(this, clazz)
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    private val localeChangedListener: Activity.(Context) -> Unit = {
        when {
            shouldRestartApplication -> {
                Log.w(TAG, "restarting application")
                startActivity()
                exitProcess(0)
            }
            shouldRestartActivity -> {
                Log.w(TAG, "restarting activity")
                startActivity()
                finishActivity(0)
            }
            shouldRecreateActivity -> {
                Log.w(TAG, "recreating activity")
                recreate()
            }
        }
    }

    override fun Activity.activityAttachBaseContext(newBase: Context) =
        if (newBase.restoreInBaseContextOfActivity) newBase.restoreLocale() else newBase

    override fun Activity.activityOnCreate() {
        usedActivity = WeakReference(this)
        addLocaleListener(localeChangedListener)
    }

    override fun Activity.activityOnConfigurationChanged(newConfig: Configuration): Configuration {
        if (restoreInConfigChangedOfActivity) restoreLocale()
        return newConfig
    }

    override fun Activity.activityApplyOverrideConfiguration(overrideConfiguration: Configuration) =
        if (restoreInApplyOverrideConfiguration) updateConfiguration(overrideConfiguration)
        else overrideConfiguration

    override fun Activity.activityOnDestroy() = removeLocaleListener(localeChangedListener)

    override fun Application.applicationAttachBaseContext(newBase: Context) =
        if (newBase.restoreInBaseContextOfApplication) newBase.restoreLocale() else newBase

    override fun Application.applicationOnConfigurationChanged(newConfig: Configuration): Configuration {
        if (restoreInConfigChangedOfApplication) restoreLocale()
        return newConfig
    }
}