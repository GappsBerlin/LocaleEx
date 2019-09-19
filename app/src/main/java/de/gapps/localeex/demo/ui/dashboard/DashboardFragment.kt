package de.gapps.localeex.demo.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import de.gapps.localeex.demo.R
import kotlinx.android.synthetic.main.fragment_sample_page.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_sample_page, container, false)
        dashboardViewModel.actText.observe(this, Observer {
            upperText.text = it
        })
        dashboardViewModel.appText.observe(this, Observer {
            lowerText.text = it
        })
        return root
    }
}