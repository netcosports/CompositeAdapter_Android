package com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.databinding.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.sample.differentbindings.BR

// ViewBinding is better anyway
interface DataBindingCell<T> : Cell<T> {

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return DataBindingViewHolder.create(inflater, layoutId, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DataBindingViewHolder).apply {
            bindings.setVariable(BR.item, data)
            bindings.executePendingBindings()
        }
    }
}