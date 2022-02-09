/*
 * Copyright 2021-2022 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
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
import com.originsdigital.compositeadapter.cell.ClickItem
import com.originsdigital.compositeadapter.cell.GenericCell
import com.originsdigital.compositeadapter.utils.getCompositeAdapterItem
import com.originsdigital.compositeadapter.utils.getCompositeAdapterViewHolder
import com.originsdigital.compositeadapter.utils.setCompositeAdapterItem
import com.originsdigital.compositeadapter.utils.setCompositeAdapterViewHolder

abstract class BaseCompositeAdapter<CELL : GenericCell>(
    config: AsyncDifferConfig<GenericCell>
) : ListAdapter<GenericCell, RecyclerView.ViewHolder>(config) {

    private val innerClickListener: View.OnClickListener = View.OnClickListener { view ->
        dispatchClick(holder = view.getCompositeAdapterViewHolder())
    }

    private lateinit var inflater: LayoutInflater
    private val typeInstances: SparseArray<GenericCell> = SparseArray()

    public override fun getItem(position: Int): Cell<*, RecyclerView.ViewHolder> {
        @Suppress("UNCHECKED_CAST")
        return super.getItem(position) as Cell<*, RecyclerView.ViewHolder>
    }

    override fun getItemViewType(position: Int): Int {
        val cell = getItem(position)
        return cell.viewType.also { viewType ->
            typeInstances.put(viewType, cell)
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
        val isInternalPayloads = (payloads.isNotEmpty()
                && payloads.all { payload -> payload == Cell.CELL_INTERNAL_PAYLOAD })
        if (isInternalPayloads) {
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
        holder.itemView.setCompositeAdapterViewHolder(holder)
    }

    protected open fun onBind(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position).onBindViewHolder(holder, position)
    }

    protected open fun onBindClickListener(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener(innerClickListener)
        holder.itemView.isClickable = getItem(position).onClickListener != null
    }

    protected open fun storeCellInHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setCompositeAdapterItem(getItem(position))
    }

    protected open fun dispatchClick(holder: RecyclerView.ViewHolder) {
        val position = holder.bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            val cell = holder.getCompositeAdapterItem<Cell<Any?, RecyclerView.ViewHolder>>()
            cell.onClickListener?.invoke(
                ClickItem(
                    holder = holder,
                    position = position,
                    item = cell.data
                )
            )
        }
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.getCompositeAdapterItem<Cell<*, RecyclerView.ViewHolder>>()
            .onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.getCompositeAdapterItem<Cell<*, RecyclerView.ViewHolder>>()
            .onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        holder.getCompositeAdapterItem<Cell<*, RecyclerView.ViewHolder>>()
            .onViewRecycled(holder)
    }
}