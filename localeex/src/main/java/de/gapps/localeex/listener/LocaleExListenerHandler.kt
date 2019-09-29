package de.gapps.localeex.listener

import android.app.Activity
import android.content.Context
import android.util.Log
import de.gapps.localeex.system_callbacks.LocaleExSystemCallbackHandler
import java.util.*

internal object LocaleExListenerHandler : ILocaleExListenerHandler {

    private val TAG = LocaleExListenerHandler::class.java.simpleName

    private val listener = ArrayList<Activity.(context: Context) -> Unit>()

    override fun clearLocaleListener() = listener.clear()

    override fun addLocaleListener(listener: Activity.(context: Context) -> Unit) {
        LocaleExListenerHandler.listener.add(listener)
    }

    override fun removeLocaleListener(listener: Activity.(context: Context) -> Unit) {
        LocaleExListenerHandler.listener.remove(listener)
    }

    internal fun notifyListener(context: Context) =
        listener.forEach {
            LocaleExSystemCallbackHandler.usedActivity?.get()?.it(context)
                ?: Log.w(TAG, "notify listener without activity available")
        }
}