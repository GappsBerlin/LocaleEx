package de.gapps.localeex

import android.content.Context
import java.util.*

internal object LocaleExListenerHandler : ILocaleExListenerHandler {

    private val listener = ArrayList<(context: Context) -> Unit>()

    override fun addListener(listener: (context: Context) -> Unit) {
        this.listener.add(listener)
    }

    override fun removeListener(listener: (context: Context) -> Unit) {
        this.listener.remove(listener)
    }

    internal fun notifyListener(context: Context) = listener.forEach { it(context) }
}