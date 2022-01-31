/*
 * Copyright 2021-2022 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.utils

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.R
import com.originsdigital.compositeadapter.cell.GenericCell

val RecyclerView.ViewHolder.context: Context get() = itemView.context

fun <VIEW_HOLDER : RecyclerView.ViewHolder> View.getCompositeAdapterViewHolder(): VIEW_HOLDER {
    @Suppress("UNCHECKED_CAST")
    return getTag(R.id.composite_adapter_view_holder_tag) as VIEW_HOLDER
}

fun <VIEW_HOLDER : RecyclerView.ViewHolder> View.setCompositeAdapterViewHolder(
    holder: VIEW_HOLDER
) {
    setTag(R.id.composite_adapter_view_holder_tag, holder)
}

fun <CELL : GenericCell> CELL.getCompositeAdapterItem(viewHolder: RecyclerView.ViewHolder): CELL {
    return viewHolder.itemView.getCompositeAdapterItem()
}

fun <CELL : GenericCell> CELL.getCompositeAdapterItem(root: View): CELL {
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