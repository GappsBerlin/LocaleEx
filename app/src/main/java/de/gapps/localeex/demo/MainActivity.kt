package de.gapps.localeex.demo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import de.gapps.localeex.ISharedPreferenceHolder
import de.gapps.localeex.LocaleEx
import de.gapps.localeex.PreferenceProperty
import de.gapps.localeex.impl.LocaleExActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : LocaleExActivity(), ISharedPreferenceHolder {

    companion object {

        private const val SER_RECREATE = "SER_RECREATE"

        lateinit var activity: MainActivity

        val application: MainApplication
            get() = activity.application as MainApplication

        val service: MainService?
            get() = MainApplication.service
    }

    override val sharedPrefs: SharedPreferences
        get() = activity.getSharedPreferences(SER_RECREATE, Context.MODE_PRIVATE)

    private var shouldRecreate by PreferenceProperty(SER_RECREATE, false) { value ->
        if (value) LocaleEx.addListener(recreateListener)
        else LocaleEx.removeListener(recreateListener)
    }

    private val recreateListener: (Context) -> Unit = { activity.recreate() }

    init {
        activity = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomBar()
        if (shouldRecreate) LocaleEx.addListener(recreateListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.also { MenuInflater(this).inflate(R.menu.language_setting, it) }
        menu?.findItem(R.id.language_setting_reload_fragment)?.isChecked = shouldRecreate
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.language_setting_reload_fragment -> {
                item.isChecked = !item.isChecked
                shouldRecreate = item.isChecked
            }
            R.id.language_setting_english -> applyLocale(Locale("en"))
            R.id.language_setting_german -> applyLocale(Locale("de"))
            R.id.language_setting_french -> applyLocale(Locale("fr"))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        LocaleEx.removeListener(recreateListener)
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
