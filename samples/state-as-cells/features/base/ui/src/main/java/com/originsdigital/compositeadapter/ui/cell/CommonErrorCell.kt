package com.originsdigital.compositeadapter.ui.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.cell.GenericCell
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.ui.R
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingCell
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingViewHolder
import com.originsdigital.compositeadapter.ui.databinding.CommonErrorCellBinding
import com.originsdigital.compositeadapter.ui.entity.CommonErrorUI

data class CommonErrorCell(
    override val data: CommonErrorUI,
    override val decoration: ItemDecoration<GenericCell>?,
    override val onClickListener: ((ClickItem<CommonErrorUI>) -> Unit)?
) : ViewBindingCell<CommonErrorUI, CommonErrorCellBinding>() {

    override val uniqueId = data.id
    override val layoutId = R.layout.common_error_cell

    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): CommonErrorCellBinding {
        return CommonErrorCellBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(
        holder: ViewBindingViewHolder<CommonErrorCellBinding>,
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