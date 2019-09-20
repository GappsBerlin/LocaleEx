package de.gapps.localeex.demo.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.gapps.localeex.LocaleEx
import de.gapps.localeex.demo.MainActivity
import de.gapps.localeex.demo.MainApplication

open class DemoViewModel(private val message: Int) : ViewModel() {

    init {
        LocaleEx.addListener { reload() }
    }

    private fun reload() {
        _localeLoader()
        _actLoader()
        _appLoader()
        _srvLoader()
    }

    private var _localeLoader: () -> Unit
    private val _locale = MutableLiveData<String>().apply {
        _localeLoader = {
            value = MainActivity.activity.run { LocaleEx.run { "$storedLocale" } }
        }
        _localeLoader()
    }
    val locale: LiveData<String>
        get() = _locale

    private var _actLoader: () -> Unit
    private val _actText = MutableLiveData<String>().apply {
        _actLoader = {
            value = MainActivity.activity.run { getString(message, "Activity") }
        }
        _actLoader.invoke()
    }
    val actText: LiveData<String> = _actText

    private var _appLoader: () -> Unit
    private val _appText = MutableLiveData<String>().apply {
        _appLoader = {
            value = MainApplication.application.run { getString(message, "Application") }
        }
        _appLoader.invoke()
    }
    val appText: LiveData<String> = _appText

    private var _srvLoader: () -> Unit
    private val _srvText = MutableLiveData<String>().apply {
        _srvLoader = {
            value = MainApplication.service?.run { getString(message, "Service") } ?: "Service: null"
        }
        _srvLoader.invoke()
    }
    val srvText: LiveData<String> = _srvText
}