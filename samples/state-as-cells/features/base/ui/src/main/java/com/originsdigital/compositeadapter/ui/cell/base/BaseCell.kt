package com.originsdigital.compositeadapter.ui.cell.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.ClickItem

abstract class BaseCell<DATA, VIEW_HOLDER : BaseViewHolder> : Cell<DATA> {

    abstract fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): VIEW_HOLDER

    fun onBindViewHolder(
        holder: VIEW_HOLDER,
        position: Int,
        payloads: List<Any>
    ): Boolean = false

    abstract fun onBindViewHolder(holder: VIEW_HOLDER, position: Int)

    fun onViewAttachedToWindow(holder: VIEW_HOLDER) = Unit
    fun onViewDetachedFromWindow(holder: VIEW_HOLDER) = Unit

    fun onViewRecycled(holder: VIEW_HOLDER) = Unit

    fun onClicked(context: Context, holder: VIEW_HOLDER, position: Int) {
        onClickListener?.invoke(ClickItem(context, holder, position, data))
    }

    final override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return createViewHolder(inflater, parent, viewType)
    }

    final override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: List<Any>
    ): Boolean {
        return onBindViewHolder(castHolder(holder), position, payloads)
    }

    final override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindViewHolder(castHolder(holder), position)
    }

    final override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        onViewAttachedToWindow(castHolder(holder))
    }

    final override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        onViewDetachedFromWindow(castHolder(holder))
    }

    final override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        onViewRecycled(castHolder(holder))
    }

    final override fun onClicked(context: Context, holder: RecyclerView.ViewHolder, position: Int) {
        onClicked(context, castHolder(holder), position)
    }

    protected fun castHolder(holder: RecyclerView.ViewHolder): VIEW_HOLDER {
        @Suppress("UNCHECKED_CAST")
        return holder as VIEW_HOLDER
    }
}