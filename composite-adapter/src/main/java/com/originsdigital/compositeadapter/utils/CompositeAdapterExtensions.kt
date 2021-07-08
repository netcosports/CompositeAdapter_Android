/*
 * Copyright 2021 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.utils

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.R

val RecyclerView.ViewHolder.context: Context get() = itemView.context

var View.compositeAdapterViewHolder: RecyclerView.ViewHolder
    get() = getTag(R.id.composite_adapter_view_holder_tag) as RecyclerView.ViewHolder
    set(value) {
        setTag(R.id.composite_adapter_view_holder_tag, value)
    }

fun <ITEM> View.getCompositeAdapterItem(): ITEM {
    @Suppress("UNCHECKED_CAST")
    return getTag(R.id.composite_adapter_item_tag) as ITEM
}

fun <ITEM> View.setCompositeAdapterItem(item: ITEM) {
    setTag(R.id.composite_adapter_item_tag, item)
}

inline fun <reified VIEW_BINDING> RecyclerView.ViewHolder.getViewBinding(
    crossinline delegate: (View) -> VIEW_BINDING
): VIEW_BINDING {
    val binding = itemView.getTag(R.id.composite_adapter_viewbinding_tag)
    return if (binding == null) {
        delegate(itemView).also {
            setViewBinding(it)
        }
    } else {
        binding as VIEW_BINDING
    }
}

fun <VIEW_BINDING> RecyclerView.ViewHolder.setViewBinding(binding: VIEW_BINDING) {
    itemView.setTag(R.id.composite_adapter_viewbinding_tag, binding)
}