package com.originsdigital.compositeadapter.ui.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import com.originsdigital.compositeadapter.cell.GenericClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.ui.R
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingCell
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingViewHolder
import com.originsdigital.compositeadapter.ui.databinding.CommonFullErrorCellBinding
import com.originsdigital.compositeadapter.ui.entity.CommonErrorUI

data class CommonFullErrorCell(
    override val data: CommonErrorUI,
    override val decoration: ItemDecoration? = null,
    override val onClickListener: ((GenericClickItem<CommonErrorUI>) -> Unit)? = null
) : ViewBindingCell<CommonErrorUI, CommonFullErrorCellBinding>() {

    override val uniqueId = data.id
    override val viewType = R.layout.common_full_error_cell

    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): CommonFullErrorCellBinding {
        return CommonFullErrorCellBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(
        holder: ViewBindingViewHolder<CommonFullErrorCellBinding>,
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