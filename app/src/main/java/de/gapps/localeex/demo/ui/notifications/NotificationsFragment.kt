package de.gapps.localeex.demo.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import de.gapps.localeex.demo.R
import kotlinx.android.synthetic.main.fragment_sample_page.*

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_sample_page, container, false)
        notificationsViewModel.actText.observe(this, Observer {
            upperText.text = it
        })
        notificationsViewModel.appText.observe(this, Observer {
            lowerText.text = it
        })
        return root
    }
}