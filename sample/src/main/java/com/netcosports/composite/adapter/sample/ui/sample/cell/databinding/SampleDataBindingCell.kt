package com.netcosports.composite.adapter.sample.ui.sample.cell.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.netcosports.composite.adapter.cell.Cell
import com.netcosports.composite.adapter.sample.BR

interface SampleDataBindingCell<T> : Cell<T> {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DataBindingViewHolder.create(inflater, layoutId, parent)
    }

    override fun onBind(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DataBindingViewHolder).apply {
            bindings.setVariable(BR.item, data)
            bindings.executePendingBindings()
        }
    }

    class DataBindingViewHolder(val bindings: ViewDataBinding) : RecyclerView.ViewHolder(bindings.root) {

        companion object {
            fun create(inflater: LayoutInflater, layoutResId: Int, parent: ViewGroup): DataBindingViewHolder {
                return DataBindingViewHolder(DataBindingUtil.inflate(inflater, layoutResId, parent, false))
            }
        }
    }
}