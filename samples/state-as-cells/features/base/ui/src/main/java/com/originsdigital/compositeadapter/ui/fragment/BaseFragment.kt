package com.originsdigital.compositeadapter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import org.koin.core.component.KoinComponent

abstract class BaseFragment<VIEW_BINDING : ViewBinding> : Fragment(), KoinComponent {

    protected open var viewBinding: VIEW_BINDING? = null
    protected fun requireViewBinding(): VIEW_BINDING = requireNotNull(viewBinding)
    protected abstract fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VIEW_BINDING

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createViewBinding(inflater, container).also {
            viewBinding = it
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}