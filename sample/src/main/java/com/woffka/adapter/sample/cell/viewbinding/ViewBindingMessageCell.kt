package com.woffka.adapter.sample.cell.viewbinding

import androidx.recyclerview.widget.RecyclerView
import com.netcosports.components.adapter.recycler.cell.Cell
import com.netcosports.components.adapter.recycler.decoration.ItemDecoration
import com.netcosports.components.adapter.recycler.holder.ClickItem
import com.netcosports.components.adapter.recycler.utils.getViewBinding
import com.woffka.adapter.sample.domain.entity.MessageEntity
import com.woffka.sample.R
import com.woffka.sample.databinding.ViewbindingMessageListItemBinding

data class ViewBindingMessageCell(
    override val data: MessageEntity,
    override val decoration: ItemDecoration<out Cell<*>>?,
    override val onClickListener: ((ClickItem<MessageEntity>) -> Unit)?
) : ViewBindingCell<MessageEntity> {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.viewbinding_message_list_item

    override fun onBind(holder: RecyclerView.ViewHolder, position: Int) {
        (holder.getViewBinding(ViewbindingMessageListItemBinding::bind)).apply {
            text.text = data.text
        }
    }
}