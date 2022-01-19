package com.originsdigital.compositeadapter.ui.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import com.originsdigital.compositeadapter.ui.R
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingCell
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingViewHolder
import com.originsdigital.compositeadapter.ui.databinding.CommonFullLoaderCellBinding

data class CommonFullLoaderCell(
    override val data: Any = Unit
) : ViewBindingCell<Any, CommonFullLoaderCellBinding>() {

    override val uniqueId = "CommonFullLoaderCell"
    override val layoutId = R.layout.common_full_loader_cell

    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): CommonFullLoaderCellBinding {
        return CommonFullLoaderCellBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(
        holder: ViewBindingViewHolder<CommonFullLoaderCellBinding>,
        position: Int
    ) = Unit
}