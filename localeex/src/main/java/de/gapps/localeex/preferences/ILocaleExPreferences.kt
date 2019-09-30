package de.gapps.localeex.preferences

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import androidx.fragment.app.Fragment
import de.gapps.localeex.LocaleEx
import java.util.*
import kotlin.reflect.KClass

/**
 * Provides several properties to configure LocaleEx behaviour. If not explicitly specified else
 * the property is stored persistent.
 */
interface ILocaleExPreferences {

    /**
     * [Locale] that was set with [LocaleEx.applyLocale].
     *
     * Default is result of [Locale.getDefault].
     */
    val Context.storedLocale: Locale

    /**
     * Defines action to be done after [LocaleEx.applyLocale] was called.
     *
     * @property customActivityClass Defines [Activity] that is used for [postAction]. If `null`
     * the [Activity] that called [LocaleEx.activityOnCreate] last is used. Default is `null`.
     * Is ignored when [customIntent] is not `null`.
     * @property customIntent Defines [Intent] that is used for [postAction]. If `null` [Intent]
     * with [Activity] class [customActivityClass] is used. Default is `null`.
     */
    sealed class PostAction(
        open val customActivityClass: KClass<out Activity>? = null,
        open val customIntent: Intent? = null
    ) {

        override fun toString() = this::class.java.simpleName

        /**
         * Recreates activity by calling [Activity.recreate].
         */
        object RecreateActivity : PostAction()

        /**
         * Restarts activity by calling [Activity.startActivity] and [Activity.finishActivity].
         */
        class RestartActivity(
            override val customActivityClass: KClass<out Activity>? = null,
            override val customIntent: Intent? = null
        ) : PostAction()

        /**
         * Restarts whole application by calling [Activity.startActivity] and [System.exit].
         */
        class RestartApplication(
            override val customActivityClass: KClass<out Activity>? = null,
            override val customIntent: Intent? = null
        ) : PostAction()

        /**
         * Reloads provided [Fragment].
         */
        data class ReloadFragment(
            val fragment: Fragment? = null,
            val fragmentProvider: (() -> Fragment)? = null
        ) : PostAction()

        /**
         * Does nothing.
         */
        object Nothing : PostAction()
    }

    /**
     * Defines action to be done after [LocaleEx.applyLocale] was called.
     * Value is not stored persistent.
     *
     * Default is [PostAction.RestartApplication].
     */
    var postAction: PostAction

    /**
     * In general the [Locale] is set by calling
     * ```
     * val config = Configuration(resources.configuration)
     * config.locale = newLocale
     * resources.updateConfiguration(config, resources.displayMetrics)
     * ```
     * for Android with API < [Build.VERSION_CODES.JELLY_BEAN_MR1] or
     * ```
     * val config = Configuration(resources.configuration)
     * config.setLocale(newLocale)
     * createConfigurationContext(config)
     * ```
     * for Android with API >= [Build.VERSION_CODES.JELLY_BEAN_MR1].
     *
     * Because the results of the deprecated version and the non deprecated version differs, it is
     * possible to define deprecation handling.
     */
    enum class DeprecationHandling {
        /**
         * Respects the deprecation.
         */
        RESPECT,
        /**
         * Ignores deprecation. (always use deprecated version)
         */
        IGNORE
    }

    /**
     * Deprecation handling when [Locale] is restored with [LocaleEx.restoreLocale].
     *
     * Default is `IGNORE`.
     */
    var Context.handleDeprecationInRestore: DeprecationHandling
    /**
     * Deprecation handling when [Locale] is applied with [LocaleEx.applyLocale].
     *
     * Default is `IGNORE`.
     */
    var Context.handleDeprecationInApply: DeprecationHandling
    /**
     * Deprecation handling when [Configuration] is updated in [LocaleEx.updateConfiguration].
     *
     * Default is `IGNORE`.
     */
    var Context.handleDeprecationInUpdateConfig: DeprecationHandling

    /**
     * If `true` [LocaleEx.updateConfiguration] is called for [Configuration] in
     * [Activity.applyOverrideConfiguration].
     *
     * Default is `true`.
     */
    var Context.restoreInApplyOverrideConfiguration: Boolean
    /**
     * If `true` [LocaleEx.restoreLocale] is called on [Context] in
     * [Activity.onConfigurationChanged].
     *
     * Default is `true`.
     */
    var Context.restoreInConfigChangedOfActivity: Boolean
    /**
     * If `true` [LocaleEx.restoreLocale] is called on [Context] in
     * [Application.onConfigurationChanged].
     *
     * Default is `true`.
     */
    var Context.restoreInConfigChangedOfApplication: Boolean
    /**
     * If `true` [LocaleEx.restoreLocale] is called on [Context] in
     * [Activity.attachBaseContext].
     *
     * Default is `true`.
     */
    var Context.restoreInBaseContextOfActivity: Boolean
    /**
     * If `true` [LocaleEx.restoreLocale] is called on [Context] in
     * [Application.attachBaseContext].
     *
     * Default is `true`.
     */
    var Context.restoreInBaseContextOfApplication: Boolean
    /**
     * If `true` [LocaleEx.restoreLocale] is called on the [Application][Context] when [Locale]
     * changes.
     *
     * Default is `true`
     */
    var Context.applyLocaleToApplicationOnChange: Boolean
}

