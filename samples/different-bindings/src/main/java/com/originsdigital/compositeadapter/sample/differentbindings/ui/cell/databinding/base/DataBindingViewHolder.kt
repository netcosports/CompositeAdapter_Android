package com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.databinding.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.base.BaseViewHolder

// ViewBinding is better anyway
class DataBindingViewHolder<DATA_BINDING : ViewDataBinding>(
    val binding: DATA_BINDING
) : BaseViewHolder(binding.root) {

    companion object {
        fun <DATA_BINDING : ViewDataBinding> create(
            inflater: LayoutInflater,
            layoutResId: Int,
            parent: ViewGroup
        ): DataBindingViewHolder<DATA_BINDING> {
            return DataBindingViewHolder(
                DataBindingUtil.inflate(
                    inflater,
                    layoutResId,
                    parent,
                    false
                ) as DATA_BINDING
            )
        }
    }
}