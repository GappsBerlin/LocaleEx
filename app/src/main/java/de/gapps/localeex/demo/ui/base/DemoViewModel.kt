package de.gapps.localeex.demo.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.gapps.localeex.LocaleEx
import de.gapps.localeex.demo.MainActivity
import de.gapps.localeex.demo.MainApplication
import de.gapps.localeex.demo.R

abstract class DemoViewModel(val title: Int) : ViewModel() {

    init {
        LocaleEx.addLocaleListener { _localeResultLoader() }
        MainApplication.serviceListener.add { _localeResultLoader() }
    }

    private var _localeResultLoader: () -> Unit
    private val _localeResult = MutableLiveData<LocaleResult>().apply {
        _localeResultLoader = {
            MainActivity.apply {
                val message = R.string.message
                value = LocaleResult(
                    activity.getString(title),
                    activity.getString(message, "Selected", ""),
                    activity.getString(message, "Activity", "@${activity.resources.hashCode()}"),
                    application.getString(
                        message,
                        "Application",
                        "@${application.resources.hashCode()}"
                    ),
                    service?.getString(message, "Service", "@${service?.resources?.hashCode()}")
                        ?: "Service: null"
                )
            }
        }
        _localeResultLoader()
    }
    val localeResult: LiveData<LocaleResult>
        get() = _localeResult
}