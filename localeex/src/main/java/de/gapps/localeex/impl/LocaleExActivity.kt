package de.gapps.localeex.impl

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.gapps.localeex.ILocaleEx
import de.gapps.localeex.LocaleEx

abstract class LocaleExActivity : AppCompatActivity(), ILocaleEx by LocaleEx {

    override fun attachBaseContext(newBase: Context) =
        super.attachBaseContext(activityAttachBaseContext(newBase))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityOnCreate()
    }

    override fun onConfigurationChanged(newConfig: Configuration) =
        super.onConfigurationChanged(activityOnConfigurationChanged(newConfig))

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration) =
        super.applyOverrideConfiguration(activityApplyOverrideConfiguration(overrideConfiguration))

    override fun onDestroy() {
        activityOnDestroy()
        super.onDestroy()
    }
}