package de.gapps.localeex.listener

import android.content.Context
import java.util.*

internal object LocaleExListenerHandler : ILocaleExListenerHandler {

    private val listener = ArrayList<(context: Context) -> Unit>()

    override fun clearLocaleListener() = listener.clear()

    override fun addLocaleListener(listener: (context: Context) -> Unit) {
        LocaleExListenerHandler.listener.add(listener)
    }

    override fun removeLocaleListener(listener: (context: Context) -> Unit) {
        LocaleExListenerHandler.listener.remove(listener)
    }

    internal fun notifyListener(context: Context) = listener.forEach { it(context) }
}