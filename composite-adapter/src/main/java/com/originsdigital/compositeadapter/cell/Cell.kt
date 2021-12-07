/*
 * Copyright 2021 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.cell

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.decoration.ItemDecoration

interface Cell<T> {

    val uniqueId: String
    val data: T

    @get:LayoutRes
    val layoutId: Int
    val viewType: Int get() = layoutId

    val decoration: ItemDecoration<out Cell<*>>? get() = null
    val onClickListener: ((ClickItem<T>) -> Unit)? get() = null

    fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>): Boolean = false
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)

    fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) = Unit
    fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) = Unit

    fun onViewRecycled(holder: RecyclerView.ViewHolder) = Unit

    fun areItemsTheSame(newItem: Cell<*>): Boolean = viewType == newItem.viewType && uniqueId == newItem.uniqueId
    fun areContentsTheSame(newItem: Cell<*>): Boolean = data == newItem.data
    fun getChangePayload(newItem: Cell<*>): Any? = null
    fun areDecorationsTheSame(newItem: Cell<*>): Boolean = decoration == newItem.decoration
    fun areOnClickListenersTheSame(newItem: Cell<*>): Boolean = onClickListener == newItem.onClickListener

    fun onClicked(context: Context, holder: RecyclerView.ViewHolder, position: Int) {
        onClickListener?.invoke(ClickItem(context, holder, position, data))
    }

    companion object {
        const val CELL_INTERNAL_PAYLOAD = "CELL_INTERNAL_PAYLOAD"
    }
}