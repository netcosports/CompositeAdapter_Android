package com.originsdigital.composite.adapter.sample.ui.sample.cell.databinding

import com.originsdigital.composite.adapter.cell.Cell
import com.originsdigital.composite.adapter.cell.ClickItem
import com.originsdigital.composite.adapter.decoration.ItemDecoration
import com.originsdigital.composite.adapter.sample.R
import com.originsdigital.composite.adapter.sample.domain.entity.MessageEntity

data class SampleDataBindingMessageCell(
    override val data: MessageEntity,
    override val decoration: ItemDecoration<out Cell<*>>?,
    override val onClickListener: ((ClickItem<MessageEntity>) -> Unit)
) : SampleDataBindingCell<MessageEntity> {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.sample_databinding_message_list_item
}