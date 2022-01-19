package com.originsdigital.compositeadapter.ui.cell.viewbinding

import androidx.viewbinding.ViewBinding
import com.originsdigital.compositeadapter.ui.cell.base.BaseViewHolder

class ViewBindingViewHolder<VIEW_BINDING : ViewBinding>(
    val binding: VIEW_BINDING
) : BaseViewHolder(binding.root)