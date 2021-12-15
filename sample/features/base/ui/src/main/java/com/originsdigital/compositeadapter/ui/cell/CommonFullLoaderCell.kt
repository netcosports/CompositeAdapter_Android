package com.originsdigital.compositeadapter.ui.cell

import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.ui.R
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingCell

data class CommonFullLoaderCell(
    override val data: Any = Unit
) : ViewBindingCell<Any> {

    override val uniqueId = "CommonFullLoaderCell"
    override val layoutId = R.layout.common_full_loader_cell

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = Unit
}