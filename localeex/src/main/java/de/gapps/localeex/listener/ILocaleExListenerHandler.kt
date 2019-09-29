package de.gapps.localeex.listener

import android.app.Activity
import android.content.Context

interface ILocaleExListenerHandler {

    fun clearLocaleListener()
    fun addLocaleListener(listener: Activity.(context: Context) -> Unit)
    fun removeLocaleListener(listener: Activity.(context: Context) -> Unit)
}