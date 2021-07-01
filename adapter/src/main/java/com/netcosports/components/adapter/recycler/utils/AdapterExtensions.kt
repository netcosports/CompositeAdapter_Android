package com.netcosports.components.adapter.recycler.utils

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.netcosports.components.adapter.R

val RecyclerView.ViewHolder.context: Context get() = itemView.context

fun <ITEM> View.setCompositeAdapterItem(item: ITEM) {
    setTag(R.id.composite_adapter_item_tag, item)
}

fun <ITEM> View.getCompositeAdapterItem(): ITEM {
    @Suppress("UNCHECKED_CAST")
    return getTag(R.id.composite_adapter_item_tag) as ITEM
}

inline fun <reified VIEW_BINDING> RecyclerView.ViewHolder.getViewBinding(
    crossinline delegate: (View) -> VIEW_BINDING
): VIEW_BINDING {
    return itemView.getViewBinding(delegate)
}

inline fun <reified VIEW_BINDING> View.getViewBinding(crossinline delegate: (View) -> VIEW_BINDING): VIEW_BINDING {
    val binding = getTag(R.id.composite_adapter_viewbinding_tag)
    return if (binding == null) {
        delegate(this).also {
            setTag(R.id.composite_adapter_viewbinding_tag, it)
        }
    } else {
        binding as VIEW_BINDING
    }
}