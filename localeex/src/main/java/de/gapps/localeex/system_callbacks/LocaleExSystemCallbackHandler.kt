package de.gapps.localeex.system_callbacks

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.util.Log
import de.gapps.localeex.LocaleEx.TAG
import de.gapps.localeex.internal.ILocaleExInternal
import de.gapps.localeex.internal.LocaleExInternal
import de.gapps.localeex.listener.ILocaleExListenerHandler
import de.gapps.localeex.listener.LocaleExListenerHandler
import de.gapps.localeex.preferences.ILocaleExPreferences.PostAction.*
import java.lang.ref.WeakReference
import kotlin.reflect.KClass
import kotlin.system.exitProcess

internal object LocaleExSystemCallbackHandler : ILocaleExSystemCallbacks,
    ILocaleExInternal by LocaleExInternal,
    ILocaleExListenerHandler by LocaleExListenerHandler {

    internal var usedActivity: WeakReference<Activity>? = null
        private set

    private fun Activity.startActivity(
        clazz: KClass<out Activity>? = null,
        intent: Intent? = null
    ) {
        val c = clazz?.java ?: this::class.java
        val i = intent ?: Intent(this, c)
        Log.v(TAG, "startActivity() intent=$i")
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    private val localeChangedListener: Activity.(Context) -> Unit = {
        when (postAction) {
            is RestartApplication -> {
                startActivity(postAction.customActivityClass, postAction.customIntent)
                exitProcess(0)
            }
            is RestartActivity -> {
                startActivity(postAction.customActivityClass, postAction.customIntent)
                finishActivity(0)
            }
            is RecreateActivity -> recreate()
            is Nothing -> Unit
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