package de.gapps.localeex.demo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.widget.Toast
import de.gapps.localeex.demo.MainService.LocalBinder
import de.gapps.localeex.impl.LocaleExApplication


class MainApplication : LocaleExApplication() {

    companion object {

        lateinit var application: MainApplication

        var service: MainService? = null
    }

    private var boundService: MainService? = null
        set(value) {
            field = value
            service = value
        }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            boundService = (service as LocalBinder).service

            // Tell the user about this for our demo.
            Toast.makeText(
                this@MainApplication, R.string.local_service_connected,
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onServiceDisconnected(className: ComponentName) {
            boundService = null
            Toast.makeText(
                this@MainApplication, R.string.local_service_disconnected,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    init {
        application = this
    }

    override fun onCreate() {
        super.onCreate()

        bindService(
            Intent(this, MainService::class.java),
            connection,
            Context.BIND_AUTO_CREATE
        )
    }

    override fun onTerminate() {
        if (boundService != null) unbindService(connection)
        super.onTerminate()
    }
}