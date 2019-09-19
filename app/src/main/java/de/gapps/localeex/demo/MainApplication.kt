package de.gapps.localeex.demo

import de.gapps.localeex.impl.LocaleExApplication

class MainApplication : LocaleExApplication() {

    companion object {

        lateinit var application: MainApplication
    }

    init {
        application = this
    }
}