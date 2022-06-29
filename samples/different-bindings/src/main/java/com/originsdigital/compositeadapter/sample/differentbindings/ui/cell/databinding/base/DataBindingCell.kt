package com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.databinding.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.sample.differentbindings.BR

// ViewBinding is better anyway
abstract class DataBindingCell<DATA, DATA_BINDING : ViewDataBinding>
    : Cell<DATA, DataBindingViewHolder<DATA_BINDING>> {

    @get:LayoutRes
    abstract val layoutId: Int

    override val viewType: Int get() = layoutId

    final override fun onCreateViewHolder(
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