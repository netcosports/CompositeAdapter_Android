package com.originsdigital.compositeadapter.ui.cell

import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.ui.R
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingCell
import com.originsdigital.compositeadapter.ui.databinding.CommonFullEmptyCellBinding
import com.originsdigital.compositeadapter.ui.entity.CommonErrorUI
import com.originsdigital.compositeadapter.utils.getViewBinding

data class CommonFullEmptyCell(
    override val data: CommonErrorUI,
    override val decoration: ItemDecoration<out Cell<*>>?,
    override val onClickListener: ((ClickItem<CommonErrorUI>) -> Unit)?
) : ViewBindingCell<CommonErrorUI> {

    override val uniqueId = data.id
    override val layoutId = R.layout.common_full_empty_cell

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder.getViewBinding(CommonFullEmptyCellBinding::bind)).apply {
            text.text = when (data.message) {
                is CommonErrorUI.Message.Text -> data.message.text
                is CommonErrorUI.Message.Resource -> text.context.getString(data.message.textId)
            }
        }
    }
}