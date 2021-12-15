package com.originsdigital.compositeadapter.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.originsdigital.compositeadapter.ui.databinding.CommonRecyclerFragmentBinding

abstract class SimpleRecyclerFragment : BaseFragment<CommonRecyclerFragmentBinding>() {

    final override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CommonRecyclerFragmentBinding {
        return CommonRecyclerFragmentBinding.inflate(inflater, container, false)
    }
}