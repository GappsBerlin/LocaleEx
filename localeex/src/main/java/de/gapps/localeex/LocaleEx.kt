@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package de.gapps.localeex

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import de.gapps.localeex.impl.LocaleExActivity
import de.gapps.localeex.impl.LocaleExApplication
import de.gapps.localeex.internal.ILocaleExInternal
import de.gapps.localeex.internal.LocaleExInternal
import de.gapps.localeex.listener.ILocaleExListenerHandler
import de.gapps.localeex.listener.LocaleExListenerHandler
import de.gapps.localeex.preferences.ILocaleExPreferences
import de.gapps.localeex.system_callbacks.ILocaleExSystemCallbacks
import de.gapps.localeex.system_callbacks.LocaleExSystemCallbackHandler
import java.util.*

/**
 * [LocaleEx] provides functionality to define an exclusive [Locale] in your app without changing
 * the operating systems [Locale].
 *
 * Features:
 * - applied [Locale] is stored persistent and is restored on app restart
 * - to make sure the defined [Locale] is also set for your [Application]s [Context] and any or your
 *   [Service]s [Context]s the app is automatically restarted when a new [Locale] is set by the user
 * - to fine tune [LocaleEx] for your needs nearly every action can be configured. see the
 *   [ILocaleExPreferences] interface and demo app for all available options and how they effect
 *   the [Locale].
 *
 *
 * To use [LocaleEx] you need to extends all of your [Activity]s from [LocaleExActivity] and your
 * [Application] from [LocaleExApplication]. If you do not have a custom [Application] please add
 * the following to your `application` tag in your `Manifest.xml`:
 * ```
 *<application
 *    android:name="de.gapps.localeex.impl.LocaleExApplication"
 * ```
 *
 * Now you just need to call `applyLocale` to define you custom [Locale]:
 * ```
 * LocaleEx.applyLocale(Locale("en", "EN"))
 * ```
 *
 * If you do not want to extend your [Activity]s and [Application] from the [LocaleEx] ones you can
 * also let them implement `ILocaleEx by LocaleEx` and copy the appropriate [LocaleEx] calls
 * to your implementations.
 */
object LocaleEx : ILocaleEx,
    ILocaleExInternal by LocaleExInternal,
    ILocaleExSystemCallbacks by LocaleExSystemCallbackHandler,
    ILocaleExListenerHandler by LocaleExListenerHandler {

    internal val TAG = LocaleEx::class.java.simpleName
}