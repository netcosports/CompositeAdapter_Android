package com.woffka.adapter.sample.cell.databinding

import com.netcosports.components.adapter.recycler.cell.Cell
import com.netcosports.components.adapter.recycler.decoration.ItemDecoration
import com.netcosports.components.adapter.recycler.holder.ClickItem
import com.woffka.adapter.sample.domain.entity.MessageEntity
import com.woffka.sample.R

data class DataBindingMessageCell(
    override val data: MessageEntity,
    override val decoration: ItemDecoration<out Cell<*>>?,
    override val onClickListener: ((ClickItem<MessageEntity>) -> Unit)
) : DataBindingCell<MessageEntity> {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.databinding_message_list_item
}