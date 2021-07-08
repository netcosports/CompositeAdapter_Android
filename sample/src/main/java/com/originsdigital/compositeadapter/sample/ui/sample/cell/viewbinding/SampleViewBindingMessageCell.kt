package com.originsdigital.compositeadapter.sample.ui.sample.cell.viewbinding

import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.R
import com.originsdigital.compositeadapter.sample.databinding.SampleViewbindingMessageListItemBinding
import com.originsdigital.compositeadapter.sample.domain.entity.MessageEntity
import com.originsdigital.compositeadapter.utils.getViewBinding

data class SampleViewBindingMessageCell(
    override val data: MessageEntity,
    override val decoration: ItemDecoration<out Cell<*>>?,
    override val onClickListener: ((ClickItem<MessageEntity>) -> Unit)?
) : SampleViewBindingCell<MessageEntity> {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.sample_viewbinding_message_list_item

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder.getViewBinding(SampleViewbindingMessageListItemBinding::bind)).apply {
            text.text = data.text
        }
    }
}