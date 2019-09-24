package de.gapps.localeex.demo.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.gapps.localeex.LocaleEx
import de.gapps.localeex.demo.MainActivity
import de.gapps.localeex.demo.R

abstract class DemoViewModel(val title: Int) : ViewModel() {

    init {
        LocaleEx.addLocaleListener { _localeResultLoader() }
    }

    private var _localeResultLoader: () -> Unit
    private val _localeResult = MutableLiveData<LocaleResult>().apply {
        _localeResultLoader = {
            MainActivity.apply {
                val message = R.string.message
                value = LocaleResult(
                    activity.getString(title),
                    activity.getString(message, "Selected"),
                    activity.getString(message, "Activity"),
                    application.getString(message, "Application"),
                    service?.getString(message, "Service") ?: "Service: null"
                )
            }
        }
        _localeResultLoader()
    }
    val localeResult: LiveData<LocaleResult>
        get() = _localeResult
}