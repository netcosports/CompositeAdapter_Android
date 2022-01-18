package com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.viewbinding.base

import androidx.viewbinding.ViewBinding
import com.originsdigital.compositeadapter.sample.differentbindings.ui.cell.base.BaseViewHolder

class ViewBindingViewHolder<VIEW_BINDING : ViewBinding>(
    val binding: VIEW_BINDING
) : BaseViewHolder(binding.root)