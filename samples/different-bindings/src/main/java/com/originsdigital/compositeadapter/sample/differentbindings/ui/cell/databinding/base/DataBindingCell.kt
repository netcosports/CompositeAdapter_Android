package com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.databinding.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.originsdigital.compositeadapter.sample.differentbindings.BR
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.base.BaseCell

// ViewBinding is better anyway
abstract class DataBindingCell<DATA, DATA_BINDING : ViewDataBinding>
    : BaseCell<DATA, DataBindingViewHolder<DATA_BINDING>>() {

    final override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): DataBindingViewHolder<DATA_BINDING> {
        return DataBindingViewHolder.create(inflater, layoutId, parent)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<DATA_BINDING>, position: Int) {
        (holder.binding).apply {
            setVariable(BR.item, data)
            executePendingBindings()
        }
    }
}