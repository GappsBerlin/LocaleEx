package de.gapps.localeex.impl

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import de.gapps.localeex.ILocaleEx
import de.gapps.localeex.LocaleEx
import kotlin.reflect.KClass
import kotlin.system.exitProcess

abstract class LocaleExActivity : AppCompatActivity(), ILocaleEx by LocaleEx {

    abstract val activityToStart: KClass<out Activity>

    private fun startMainActivity() {
        val i = Intent(this, activityToStart.java)
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    private val localeChangedListener: (Context) -> Unit = {
        when {
            shouldRestartApplication -> {
                Log.w(LocaleEx.TAG, "restarting application")
                startMainActivity()
                exitProcess(0)
            }
            shouldRestartActivity -> {
                Log.w(LocaleEx.TAG, "restarting activity")
                startMainActivity()
                finishActivity(0)
            }
            shouldRecreateActivity -> {
                Log.w(LocaleEx.TAG, "recreating activity")
                recreate()
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(if (newBase.restoreInBaseContextOfActivity) newBase.restoreLocale() else newBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addLocaleListener(localeChangedListener)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (restoreInConfigChangedOfActivity) restoreLocale()
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration) =
        super.applyOverrideConfiguration(
            if (restoreInApplyOverrideConfiguration) updateConfiguration(overrideConfiguration)
            else overrideConfiguration
        )

    override fun onDestroy() {
        removeLocaleListener(localeChangedListener)
        super.onDestroy()
    }
}