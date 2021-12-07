/*
 * Copyright 2021 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.adapter

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.utils.compositeAdapterViewHolder
import com.originsdigital.compositeadapter.utils.context
import com.originsdigital.compositeadapter.utils.getCompositeAdapterItem
import com.originsdigital.compositeadapter.utils.setCompositeAdapterItem

abstract class BaseCompositeAdapter<DATA : Cell<*>>(
    config: AsyncDifferConfig<DATA>
) : ListAdapter<DATA, RecyclerView.ViewHolder>(config) {

    private val innerClickListener: View.OnClickListener = View.OnClickListener { v ->
        val holder = v.compositeAdapterViewHolder
        val position = holder.bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            getItem(position).onClicked(holder.context, holder, position)
        }
    }

    private lateinit var inflater: LayoutInflater
    private val typeInstances: SparseArray<DATA> = SparseArray()

    public override fun getItem(position: Int): DATA = super.getItem(position)

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return item.viewType.also {
            typeInstances.put(it, item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        val item = typeInstances[viewType]
        return item.onCreateViewHolder(inflater, parent, item.viewType)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty() && payloads.all { it == Cell.CELL_INTERNAL_PAYLOAD }) {
            handleInternalPayload(holder, position, payloads)
        } else {
            if (!getItem(position).onBindViewHolder(holder, position, payloads)) {
                super.onBindViewHolder(holder, position, payloads)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        storeHolderInView(holder, position)
        storeCellInHolder(holder, position)
        onBindClickListener(holder, position)
        onBind(holder, position)
    }

    protected open fun handleInternalPayload(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        storeCellInHolder(holder, position)
        onBindClickListener(holder, position)
    }

    protected open fun storeHolderInView(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.compositeAdapterViewHolder = holder
    }

    protected open fun onBind(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position).onBindViewHolder(holder, position)
    }

    protected open fun onBindClickListener(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener(innerClickListener)
        holder.itemView.isClickable = getItem(position).onClickListener != null
    }

    protected open fun storeCellInHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setCompositeAdapterItem(getItem(position))
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.getCompositeAdapterItem<DATA>().onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.getCompositeAdapterItem<DATA>().onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        holder.itemView.getCompositeAdapterItem<DATA>().onViewRecycled(holder)
    }
}