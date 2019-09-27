package de.gapps.localeex.demo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import de.gapps.localeex.impl.LocaleExActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.reflect.KClass

class MainActivity : LocaleExActivity() {

    companion object {

        lateinit var activity: MainActivity

        val application: MainApplication
            get() = activity.application as MainApplication

        val service: MainService?
            get() = MainApplication.service
    }

    override val activityToStart: KClass<out Activity>
        get() = MainActivity::class

    init {
        activity = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomBar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean = menu?.run {
        MenuInflater(this@MainActivity).inflate(R.menu.language_setting, this)
        findItem(R.id.language_setting_reload_fragment)?.isChecked = shouldRecreateActivity
        findItem(R.id.language_setting_restart_act)?.isChecked = shouldRestartActivity
        findItem(R.id.language_setting_restart_app)?.isChecked = shouldRestartApplication
        findItem(R.id.language_setting_override_config)?.isChecked =
            restoreInApplyOverrideConfiguration
        findItem(R.id.language_setting_config_changed_act)?.isChecked =
            restoreInConfigChangedOfActivity
        findItem(R.id.language_setting_config_changed_app)?.isChecked =
            restoreInConfigChangedOfApplication
        findItem(R.id.language_setting_restore_base_context_act)?.isChecked =
            restoreInBaseContextOfActivity
        findItem(R.id.language_setting_restore_base_context_app)?.isChecked =
            restoreInBaseContextOfApplication
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
            R.id.language_setting_restart_act -> {
                item.isChecked = !item.isChecked
                shouldRestartActivity = item.isChecked
            }
            R.id.language_setting_restart_app -> {
                item.isChecked = !item.isChecked
                shouldRestartApplication = item.isChecked
            }
            R.id.language_setting_override_config -> {
                item.isChecked = !item.isChecked
                restoreInApplyOverrideConfiguration = item.isChecked
            }
            R.id.language_setting_config_changed_act -> {
                item.isChecked = !item.isChecked
                restoreInConfigChangedOfActivity = item.isChecked
            }
            R.id.language_setting_config_changed_app -> {
                item.isChecked = !item.isChecked
                restoreInConfigChangedOfApplication = item.isChecked
            }
            R.id.language_setting_handle_deprecation_restore -> {
                item.isChecked = !item.isChecked
                handleDeprecationInRestore = item.isChecked
            }
            R.id.language_setting_restore_base_context_act -> {
                item.isChecked = !item.isChecked
                restoreInBaseContextOfActivity = item.isChecked
            }
            R.id.language_setting_restore_base_context_app -> {
                item.isChecked = !item.isChecked
                restoreInBaseContextOfApplication = item.isChecked
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
                    "MainActivity", "new language selected=R.string.$entryName"
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
