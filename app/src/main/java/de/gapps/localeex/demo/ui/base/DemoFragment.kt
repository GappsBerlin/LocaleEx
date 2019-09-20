package de.gapps.localeex.demo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import de.gapps.localeex.LocaleEx
import de.gapps.localeex.demo.R
import kotlinx.android.synthetic.main.fragment_sample_page.*
import kotlin.reflect.KClass

abstract class DemoFragment<VM: DemoViewModel> : Fragment(),
    IDemoFragment {

    protected abstract val viewModelClass: KClass<VM>

    private val viewModel: VM by lazy { ViewModelProviders.of(this).get(viewModelClass.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_sample_page, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.locale.observe(this, Observer {
            language_stored.text = it
        })
        viewModel.actText.observe(this, Observer {
            upperText.text = it
        })
        viewModel.appText.observe(this, Observer {
            middleText.text = it
        })
        viewModel.srvText.observe(this, Observer {
            lowerText.text = it
        })
    }
}