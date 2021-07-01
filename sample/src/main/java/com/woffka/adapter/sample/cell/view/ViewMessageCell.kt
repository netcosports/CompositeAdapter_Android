package com.woffka.adapter.sample.cell.view

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.netcosports.components.adapter.recycler.cell.Cell
import com.netcosports.components.adapter.recycler.decoration.ItemDecoration
import com.netcosports.components.adapter.recycler.holder.ClickItem
import com.woffka.adapter.sample.domain.entity.MessageEntity
import com.woffka.sample.R


data class ViewMessageCell(
    override val data: MessageEntity,
    override val decoration: ItemDecoration<out Cell<*>>?,
    override val onClickListener: ((ClickItem<MessageEntity>) -> Unit)
) : Cell<MessageEntity> {

    override val uniqueId: String = data.id
    override val layoutId: Int = R.layout.view_message_list_item

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20f, parent.resources.displayMetrics).toInt()
        return ViewMessageViewHolder(
            AppCompatTextView(inflater.context).apply {
                id = R.id.text
                layoutParams = RecyclerView.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT
                )
                setPadding(padding, padding, padding, padding)
                val outValue = TypedValue()
                context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
                setBackgroundResource(outValue.resourceId)
                TextViewCompat.setTextAppearance(this, R.style.TextAppearance_AppCompat_Medium)
            }
        )
    }

    override fun onBind(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewMessageViewHolder).apply {
            text.text = data.text
        }
    }

    private class ViewMessageViewHolder(val text: TextView) : RecyclerView.ViewHolder(text)
}