package com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.databinding.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

// ViewBinding is better anyway
class DataBindingViewHolder(
    val bindings: ViewDataBinding
) : RecyclerView.ViewHolder(bindings.root) {

    companion object {
        fun create(
            inflater: LayoutInflater,
            layoutResId: Int,
            parent: ViewGroup
        ): DataBindingViewHolder {
            return DataBindingViewHolder(
                DataBindingUtil.inflate(
                    inflater,
                    layoutResId,
                    parent,
                    false
                )
            )
        }
    }
}