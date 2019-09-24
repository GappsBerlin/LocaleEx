package de.gapps.localeex

import android.content.Context
import java.util.*

internal object LocaleExListenerHandler : ILocaleExListenerHandler {

    private val listener = ArrayList<(context: Context) -> Unit>()

    override fun clearLocaleListener() = listener.clear()

    override fun addLocaleListener(listener: (context: Context) -> Unit) {
        this.listener.add(listener)
    }

    override fun removeLocaleListener(listener: (context: Context) -> Unit) {
        this.listener.remove(listener)
    }

    internal fun notifyListener(context: Context) = listener.forEach { it(context) }
}