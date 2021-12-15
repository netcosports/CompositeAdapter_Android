package com.originsdigital.compositeadapter.ui.cell

import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.ui.R
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingCell
import com.originsdigital.compositeadapter.ui.databinding.CommonLoaderCellBinding
import com.originsdigital.compositeadapter.ui.entity.CommonLoaderUI
import com.originsdigital.compositeadapter.utils.context
import com.originsdigital.compositeadapter.utils.getViewBinding

data class CommonLoaderCell(
    override val data: CommonLoaderUI,
    override val decoration: ItemDecoration<out Cell<*>>?
) : ViewBindingCell<CommonLoaderUI> {

    override val uniqueId = data.id
    override val layoutId = R.layout.common_loader_cell

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder.getViewBinding(CommonLoaderCellBinding::bind)).apply {
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