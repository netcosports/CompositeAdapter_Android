package com.netcosports.composite.adapter.utils

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.netcosports.composite.adapter.R

val RecyclerView.ViewHolder.context: Context get() = itemView.context

fun <ITEM> View.setCompositeAdapterItem(item: ITEM) {
    setTag(R.id.composite_adapter_item_tag, item)
}

fun <ITEM> View.getCompositeAdapterItem(): ITEM {
    @Suppress("UNCHECKED_CAST")
    return getTag(R.id.composite_adapter_item_tag) as ITEM
}

fun <VIEW_BINDING> RecyclerView.ViewHolder.setViewBinding(binding: VIEW_BINDING) {
    itemView.setTag(R.id.composite_adapter_viewbinding_tag, binding)
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