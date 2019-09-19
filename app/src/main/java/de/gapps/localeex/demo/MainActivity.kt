package de.gapps.localeex.demo

import android.os.Bundle
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

class MainActivity : LocaleExActivity() {

    companion object {

        lateinit var activity: MainActivity
    }

    init {
        activity = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )

        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.also { MenuInflater(this).inflate(R.menu.language_setting, it) }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.language_setting_english -> applyNewLocale(Locale("en"))
            R.id.language_setting_german -> applyNewLocale(Locale("de"))
            R.id.language_setting_french -> applyNewLocale(Locale("fr"))
        }
        return super.onOptionsItemSelected(item)
    }
}
