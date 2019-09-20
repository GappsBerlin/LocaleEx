package de.gapps.localeex.demo.ui.pages

import de.gapps.localeex.demo.R
import de.gapps.localeex.demo.ui.base.DemoFragment
import de.gapps.localeex.demo.ui.base.DemoViewModel

class HomeViewModel : DemoViewModel(R.string.home_fragment_message)

class HomeFragment : DemoFragment<HomeViewModel>() {
    override val viewModelClass = HomeViewModel::class
}