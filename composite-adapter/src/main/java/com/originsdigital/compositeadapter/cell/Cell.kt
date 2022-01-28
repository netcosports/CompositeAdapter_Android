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

typealias GenericCell = Cell<out Any?, out RecyclerView.ViewHolder>

interface Cell<T, VIEW_HOLDER : RecyclerView.ViewHolder> {

    val uniqueId: String
    val data: T

    @get:LayoutRes
    val layoutId: Int
    val viewType: Int get() = layoutId

    val decoration: ItemDecoration<GenericCell>? get() = null

    val onClickListener: ((ClickItem<T>) -> Unit)? get() = null

    fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): VIEW_HOLDER

    fun onBindViewHolder(holder: VIEW_HOLDER, position: Int, payloads: List<Any>): Boolean = false
    fun onBindViewHolder(holder: VIEW_HOLDER, position: Int)

    fun onViewAttachedToWindow(holder: VIEW_HOLDER) = Unit
    fun onViewDetachedFromWindow(holder: VIEW_HOLDER) = Unit

    fun onViewRecycled(holder: VIEW_HOLDER) = Unit

    fun areItemsTheSame(newItem: GenericCell): Boolean = viewType == newItem.viewType && uniqueId == newItem.uniqueId
    fun areContentsTheSame(newItem: GenericCell): Boolean = data == newItem.data
    fun getChangePayload(newItem: GenericCell): Any? = null
    fun areDecorationsTheSame(newItem: GenericCell): Boolean = decoration == newItem.decoration
    fun areOnClickListenersTheSame(newItem: GenericCell): Boolean = onClickListener == newItem.onClickListener

    fun onClicked(context: Context, holder: VIEW_HOLDER, position: Int) {
        onClickListener?.invoke(ClickItem(context, holder, position, data))
    }

    companion object {
        const val CELL_INTERNAL_PAYLOAD = "CELL_INTERNAL_PAYLOAD"
    }
}