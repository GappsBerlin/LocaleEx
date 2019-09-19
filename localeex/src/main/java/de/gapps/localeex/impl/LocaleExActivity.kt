package de.gapps.localeex.impl

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import de.gapps.localeex.ILocaleEx
import de.gapps.localeex.LocaleEx

open class LocaleExActivity : AppCompatActivity(), ILocaleEx by LocaleEx {
    override fun attachBaseContext(newBase: Context) =
        super.attachBaseContext(newBase.restoreLocale())

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration) =
        super.applyOverrideConfiguration(updateConfiguration(overrideConfiguration))
}