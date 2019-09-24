package de.gapps.localeex.demo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import de.gapps.localeex.LocaleEx.shouldRecreateActivity
import de.gapps.localeex.LocaleExPreferences
import de.gapps.localeex.LocaleExPreferences.handleDeprecationInApply
import de.gapps.localeex.LocaleExPreferences.handleDeprecationInRestore
import de.gapps.localeex.LocaleExPreferences.handleDeprecationInUpdateConfig
import de.gapps.localeex.impl.LocaleExActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : LocaleExActivity() {

    companion object {

        lateinit var activity: MainActivity

        val application: MainApplication
            get() = activity.application as MainApplication

        val service: MainService?
            get() = MainApplication.service
    }

    private val recreateListener: (Context) -> Unit = { recreate() }

    init {
        activity = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomBar()
        if (shouldRecreateActivity) addLocaleListener(recreateListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean = menu?.run {
        MenuInflater(this@MainActivity).inflate(R.menu.language_setting, this)
        findItem(R.id.language_setting_reload_fragment)?.isChecked = shouldRecreateActivity
        findItem(R.id.language_setting_handle_deprecation_restore)?.isChecked =
            handleDeprecationInRestore
        findItem(R.id.language_setting_handle_deprecation_apply)?.isChecked =
            handleDeprecationInApply
        findItem(R.id.language_setting_handle_deprecation_update_configuration)?.isChecked =
            handleDeprecationInUpdateConfig
        true
    } ?: false

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.language_setting_reload_fragment -> {
                item.isChecked = !item.isChecked
                shouldRecreateActivity = item.isChecked
            }
            R.id.language_setting_handle_deprecation_restore -> {
                item.isChecked = !item.isChecked
                handleDeprecationInRestore = item.isChecked
            }
            R.id.language_setting_handle_deprecation_apply -> {
                item.isChecked = !item.isChecked
                handleDeprecationInApply = item.isChecked
            }
            R.id.language_setting_handle_deprecation_update_configuration -> {
                item.isChecked = !item.isChecked
                handleDeprecationInUpdateConfig = item.isChecked
            }
            else -> {
                val entryName = resources.getResourceEntryName(item.itemId)
                Log.v(
                    LocaleExPreferences::class.java.simpleName,
                    "new language selected=R.string.$entryName"
                )
                applyLocale(
                    when (item.itemId) {
                        R.id.language_setting_english -> Locale("en", "EN")
                        R.id.language_setting_german -> Locale("de", "DE")
                        R.id.language_setting_french -> Locale("fr", "FR")
                        else -> throw IllegalArgumentException("unknown language id R.string.$entryName")
                    }
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        removeLocaleListener(recreateListener)
        super.onDestroy()
    }

    private fun initBottomBar() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )

        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
    }
}
