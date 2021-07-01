package com.netcosports.composite.adapter.cell

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.netcosports.composite.adapter.decoration.ItemDecoration
import com.netcosports.composite.adapter.holder.ClickItem

interface Cell<T> {

    val uniqueId: String
    val data: T

    @get:LayoutRes
    val layoutId: Int
    val viewType: Int get() = layoutId

    val decoration: ItemDecoration<out Cell<*>>? get() = null
    val onClickListener: ((ClickItem<T>) -> Unit)? get() = null

    fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    fun onBind(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>): Boolean = false
    fun onBind(holder: RecyclerView.ViewHolder, position: Int)

    fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) = Unit
    fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) = Unit

    fun onViewRecycled(holder: RecyclerView.ViewHolder) = Unit

    fun areItemsTheSame(newItem: Cell<*>): Boolean = viewType == newItem.viewType && uniqueId == newItem.uniqueId
    fun areContentsTheSame(newItem: Cell<*>): Boolean = data == newItem.data
    fun getChangePayload(newItem: Cell<*>): Any? = null
    fun areDecorationsTheSame(newItem: Cell<*>): Boolean = decoration == newItem.decoration

    fun onClicked(context: Context, holder: RecyclerView.ViewHolder, position: Int) {
        onClickListener?.invoke(ClickItem(context, holder, position, data))
    }

    companion object {
        const val CELL_DECORATION_PAYLOAD = "CELL_DECORATION"
    }
}