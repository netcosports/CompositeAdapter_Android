package com.originsdigital.compositeadapter.ui.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.ui.R
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingCell
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingViewHolder
import com.originsdigital.compositeadapter.ui.databinding.CommonLoaderCellBinding
import com.originsdigital.compositeadapter.ui.entity.CommonLoaderUI
import com.originsdigital.compositeadapter.utils.context

data class CommonLoaderCell(
    override val data: CommonLoaderUI,
    override val decoration: ItemDecoration<out Cell<*>>?
) : ViewBindingCell<CommonLoaderUI, CommonLoaderCellBinding>() {

    override val uniqueId = data.id
    override val layoutId = R.layout.common_loader_cell

    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): CommonLoaderCellBinding {
        return CommonLoaderCellBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(
        holder: ViewBindingViewHolder<CommonLoaderCellBinding>,
        position: Int
    ) {
        holder.binding.apply {
            val newHeight = if (data.heightId == null) {
                ViewGroup.LayoutParams.WRAP_CONTENT
            } else {
                holder.context.resources.getDimension(data.heightId).toInt()
            }
            if (root.layoutParams.height != newHeight) {
                root.updateLayoutParams { height = newHeight }
            }
        }
    }
}