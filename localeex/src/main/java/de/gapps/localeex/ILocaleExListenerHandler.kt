package de.gapps.localeex

import android.content.Context

internal interface ILocaleExListenerHandler {

    fun addListener(listener: (context: Context) -> Unit)
    fun removeListener(listener: (context: Context) -> Unit)
}