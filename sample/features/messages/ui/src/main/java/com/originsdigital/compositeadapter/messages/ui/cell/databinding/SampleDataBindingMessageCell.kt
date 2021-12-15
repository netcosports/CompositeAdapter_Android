package com.originsdigital.compositeadapter.messages.ui.cell.databinding

import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.decoration.ItemDecoration
import com.originsdigital.compositeadapter.messages.core.entity.MessageEntity
import com.originsdigital.compositeadapter.messages.ui.R
import com.originsdigital.compositeadapter.ui.cell.databinding.DataBindingCell

data class SampleDataBindingMessageCell(
    override val data: MessageEntity,
    override val decoration: ItemDecoration<out Cell<*>>?,
    override val onClickListener: ((ClickItem<MessageEntity>) -> Unit)
) : DataBindingCell<MessageEntity> {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.message_databinding_list_item
}