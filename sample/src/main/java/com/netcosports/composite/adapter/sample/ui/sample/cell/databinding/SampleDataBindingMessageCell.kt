package com.netcosports.composite.adapter.sample.ui.sample.cell.databinding

import com.netcosports.composite.adapter.cell.Cell
import com.netcosports.composite.adapter.decoration.ItemDecoration
import com.netcosports.composite.adapter.cell.ClickItem
import com.netcosports.composite.adapter.sample.R
import com.netcosports.composite.adapter.sample.domain.entity.MessageEntity

data class SampleDataBindingMessageCell(
    override val data: MessageEntity,
    override val decoration: ItemDecoration<out Cell<*>>?,
    override val onClickListener: ((ClickItem<MessageEntity>) -> Unit)
) : SampleDataBindingCell<MessageEntity> {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.sample_databinding_message_list_item
}