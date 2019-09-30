package de.gapps.localeex

import android.content.Context
import de.gapps.localeex.internal.ILocaleExInternal
import de.gapps.localeex.listener.ILocaleExListenerHandler
import de.gapps.localeex.system_callbacks.ILocaleExSystemCallbacks
import java.util.*

interface ILocaleEx : ILocaleExSystemCallbacks, ILocaleExListenerHandler, ILocaleExInternal {

    var Context.locale: Locale
}

