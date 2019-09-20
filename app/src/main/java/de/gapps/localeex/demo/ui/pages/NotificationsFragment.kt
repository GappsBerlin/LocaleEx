package de.gapps.localeex.demo.ui.pages

import de.gapps.localeex.demo.R
import de.gapps.localeex.demo.ui.base.DemoFragment
import de.gapps.localeex.demo.ui.base.DemoViewModel

class NotificationsViewModel : DemoViewModel(R.string.notifications_fragment_message)

class NotificationsFragment : DemoFragment<NotificationsViewModel>() {
    override val viewModelClass = NotificationsViewModel::class
}