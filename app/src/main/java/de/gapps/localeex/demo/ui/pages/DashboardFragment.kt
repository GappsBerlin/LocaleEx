package de.gapps.localeex.demo.ui.pages

import de.gapps.localeex.demo.R
import de.gapps.localeex.demo.ui.base.DemoFragment
import de.gapps.localeex.demo.ui.base.DemoViewModel

class DashboardViewModel : DemoViewModel(R.string.dashboard)

class DashboardFragment : DemoFragment<DashboardViewModel>() {
    override val viewModelClass = DashboardViewModel::class
}