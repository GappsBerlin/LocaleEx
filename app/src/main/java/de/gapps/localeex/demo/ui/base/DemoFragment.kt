package de.gapps.localeex.demo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import de.gapps.localeex.demo.R
import de.gapps.localeex.demo.databinding.FragmentSamplePageBinding
import kotlin.reflect.KClass

abstract class DemoFragment<VM : DemoViewModel> : Fragment() {

    protected abstract val viewModelClass: KClass<VM>

    private val viewModel: VM by lazy { ViewModelProviders.of(this).get(viewModelClass.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DataBindingUtil.inflate<FragmentSamplePageBinding>(
        inflater, R.layout.fragment_sample_page, container, false
    ).run {
        viewModel.localeResult.observe(this@DemoFragment, Observer {
            localeResult = it
        })
        root
    }
}