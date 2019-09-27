package de.gapps.localeex.listener

import android.content.Context

interface ILocaleExListenerHandler {

    fun clearLocaleListener()
    fun addLocaleListener(listener: (context: Context) -> Unit)
    fun removeLocaleListener(listener: (context: Context) -> Unit)
}