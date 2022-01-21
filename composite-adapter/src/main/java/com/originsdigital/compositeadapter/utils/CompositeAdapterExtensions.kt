/*
 * Copyright 2021 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.utils

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.R
import com.originsdigital.compositeadapter.cell.Cell

val RecyclerView.ViewHolder.context: Context get() = itemView.context

var View.compositeAdapterViewHolder: RecyclerView.ViewHolder
    get() = getTag(R.id.composite_adapter_view_holder_tag) as RecyclerView.ViewHolder
    set(value) {
        setTag(R.id.composite_adapter_view_holder_tag, value)
    }

fun <CELL : Cell<*>> CELL.getCompositeAdapterItem(viewHolder: RecyclerView.ViewHolder): CELL {
    return viewHolder.itemView.getCompositeAdapterItem()
}

fun <CELL : Cell<*>> CELL.getCompositeAdapterItem(root: View): CELL {
    return root.getCompositeAdapterItem()
}

fun <ITEM> RecyclerView.ViewHolder.getCompositeAdapterItem(): ITEM {
    return itemView.getCompositeAdapterItem()
}

fun <ITEM> RecyclerView.ViewHolder.setCompositeAdapterItem(item: ITEM) {
    return itemView.setCompositeAdapterItem(item)
}

fun <ITEM> View.getCompositeAdapterItem(): ITEM {
    @Suppress("UNCHECKED_CAST")
    return getTag(R.id.composite_adapter_item_tag) as ITEM
}

fun <ITEM> View.setCompositeAdapterItem(item: ITEM) {
    setTag(R.id.composite_adapter_item_tag, item)
}

@Deprecated(
    level = DeprecationLevel.ERROR,
    message = "Get 'ViewBinding' from the 'ViewHolder'"
)
inline fun <reified VIEW_BINDING> RecyclerView.ViewHolder.getViewBinding(
    crossinline delegate: (View) -> VIEW_BINDING
): VIEW_BINDING {
    val binding = itemView.getTag(R.id.composite_adapter_viewbinding_tag)
    return if (binding == null) {
        delegate(itemView).also {
            @Suppress("DEPRECATION_ERROR")
            setViewBinding(it)
        }
    } else {
        binding as VIEW_BINDING
    }
}

@Deprecated(
    level = DeprecationLevel.ERROR,
    message = "Save 'ViewBinding' in the 'ViewHolder'"
)
fun <VIEW_BINDING> RecyclerView.ViewHolder.setViewBinding(binding: VIEW_BINDING) {
    itemView.setTag(R.id.composite_adapter_viewbinding_tag, binding)
}