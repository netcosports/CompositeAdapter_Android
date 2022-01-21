package com.originsdigital.compositeadapter.ui.cell.viewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.originsdigital.compositeadapter.ui.cell.base.BaseCell

abstract class ViewBindingCell<DATA, VIEW_BINDING : ViewBinding>
    : BaseCell<DATA, ViewBindingViewHolder<VIEW_BINDING>>() {

    abstract fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): VIEW_BINDING

    final override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingViewHolder<VIEW_BINDING> {
        return ViewBindingViewHolder(createViewBinding(inflater, parent, viewType))
    }
}