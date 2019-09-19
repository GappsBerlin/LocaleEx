package de.gapps.localeex.demo.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.gapps.localeex.demo.MainActivity
import de.gapps.localeex.demo.MainApplication
import de.gapps.localeex.demo.R

class NotificationsViewModel : ViewModel() {

    private val _actText = MutableLiveData<String>().apply {
        value = MainActivity.activity.getString(R.string.notifications_fragment_message)
    }
    val actText: LiveData<String> = _actText

    private val _appText = MutableLiveData<String>().apply {
        value = MainApplication.application.getString(R.string.notifications_fragment_message)
    }
    val appText: LiveData<String> = _appText
}