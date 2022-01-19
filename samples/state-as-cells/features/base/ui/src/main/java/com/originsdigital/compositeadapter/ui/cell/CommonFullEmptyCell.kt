package com.originsdigital.compositeadapter.ui.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.ui.R
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingCell
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingViewHolder
import com.originsdigital.compositeadapter.ui.databinding.CommonFullEmptyCellBinding
import com.originsdigital.compositeadapter.ui.entity.CommonErrorUI

data class CommonFullEmptyCell(
    override val data: CommonErrorUI,
    override val decoration: ItemDecoration<out Cell<*>>?,
    override val onClickListener: ((ClickItem<CommonErrorUI>) -> Unit)?
) : ViewBindingCell<CommonErrorUI, CommonFullEmptyCellBinding>() {

    override val uniqueId = data.id
    override val layoutId = R.layout.common_full_empty_cell

    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): CommonFullEmptyCellBinding {
        return CommonFullEmptyCellBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(
        holder: ViewBindingViewHolder<CommonFullEmptyCellBinding>,
        position: Int
    ) {
        holder.binding.apply {
            text.text = when (data.message) {
                is CommonErrorUI.Message.Text -> data.message.text
                is CommonErrorUI.Message.Resource -> text.context.getString(data.message.textId)
            }
        }
    }
}