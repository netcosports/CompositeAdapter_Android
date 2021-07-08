package com.originsdigital.compositeadapter.sample.ui.sample.cell.databinding

import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.sample.R
import com.originsdigital.compositeadapter.sample.domain.entity.MessageEntity

data class SampleDataBindingMessageCell(
    override val data: MessageEntity,
    override val decoration: ItemDecoration<out Cell<*>>?,
    override val onClickListener: ((ClickItem<MessageEntity>) -> Unit)
) : SampleDataBindingCell<MessageEntity> {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.sample_databinding_message_list_item
}