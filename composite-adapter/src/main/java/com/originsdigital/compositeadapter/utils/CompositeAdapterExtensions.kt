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


/**
 * @receiver A root view of the [ViewHolder][androidx.recyclerview.widget.RecyclerView.ViewHolder]
 *
 * @return the stored [VIEW_HOLDER] from the tags of the given [View].
 */
fun <VIEW_HOLDER : RecyclerView.ViewHolder> View.getCompositeAdapterViewHolder(): VIEW_HOLDER {
    @Suppress("UNCHECKED_CAST")
    return getTag(R.id.composite_adapter_view_holder_tag) as VIEW_HOLDER
}

/**
 * Stores this [ViewHolder][androidx.recyclerview.widget.RecyclerView.ViewHolder] in the tags
 * of the [root view][androidx.recyclerview.widget.RecyclerView.ViewHolder.itemView].
 *
 * @receiver The [ViewHolder][androidx.recyclerview.widget.RecyclerView.ViewHolder] to store.
 */
fun RecyclerView.ViewHolder.setCompositeAdapterViewHolder() {
    itemView.setTag(R.id.composite_adapter_view_holder_tag, this)
}


/** @return the stored [CELL] from this [ViewHolder][androidx.recyclerview.widget.RecyclerView.ViewHolder]. */
fun <CELL : GenericCell> RecyclerView.ViewHolder.getCompositeAdapterItem(): CELL {
    return itemView.getCompositeAdapterItem()
}

/** Stores [CELL] in this [ViewHolder][androidx.recyclerview.widget.RecyclerView.ViewHolder]. */
fun <CELL : GenericCell> RecyclerView.ViewHolder.setCompositeAdapterItem(cell: CELL) {
    return itemView.setCompositeAdapterItem(cell)
}

/**
 * [View] must be the root view of the [ViewHolder][androidx.recyclerview.widget.RecyclerView.ViewHolder]
 *
 * @return the stored [CELL] from the tags of the given [View].
 */
fun <CELL : GenericCell> View.getCompositeAdapterItem(): CELL {
    @Suppress("UNCHECKED_CAST")
    return getTag(R.id.composite_adapter_item_tag) as CELL
}

/**
 * Stores [CELL] in the tags of the given [View].
 *
 * [View] must be the root view of the [ViewHolder][androidx.recyclerview.widget.RecyclerView.ViewHolder]
 */
fun <CELL : GenericCell> View.setCompositeAdapterItem(cell: CELL) {
    setTag(R.id.composite_adapter_item_tag, cell)
}