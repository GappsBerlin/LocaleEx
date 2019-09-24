package de.gapps.localeex

import android.content.Context

interface ILocaleExListenerHandler {

    fun clearLocaleListener()
    fun addLocaleListener(listener: (context: Context) -> Unit)
    fun removeLocaleListener(listener: (context: Context) -> Unit)
}