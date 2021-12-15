package com.originsdigital.compositeadapter.messages.ui.cell.viewbinding

import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.messages.core.entity.MessageEntity
import com.originsdigital.compositeadapter.messages.ui.R
import com.originsdigital.compositeadapter.messages.ui.databinding.MessageViewbindingListItemBinding
import com.originsdigital.compositeadapter.ui.cell.viewbinding.ViewBindingCell
import com.originsdigital.compositeadapter.utils.getViewBinding

data class SampleViewBindingMessageCell(
    override val data: MessageEntity,
    override val decoration: ItemDecoration<out Cell<*>>?,
    override val onClickListener: ((ClickItem<MessageEntity>) -> Unit)?
) : ViewBindingCell<MessageEntity> {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.message_viewbinding_list_item

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder.getViewBinding(MessageViewbindingListItemBinding::bind)).apply {
            text.text = data.text
        }
    }
}