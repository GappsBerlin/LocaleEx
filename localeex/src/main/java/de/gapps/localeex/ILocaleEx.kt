package de.gapps.localeex

import de.gapps.localeex.internal.ILocaleExInternal
import de.gapps.localeex.listener.ILocaleExListenerHandler
import de.gapps.localeex.system_callbacks.ILocaleExSystemCallbacks

interface ILocaleEx : ILocaleExSystemCallbacks, ILocaleExListenerHandler, ILocaleExInternal

