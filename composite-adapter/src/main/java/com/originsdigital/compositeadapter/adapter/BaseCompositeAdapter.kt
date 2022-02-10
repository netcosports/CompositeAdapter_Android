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
import com.originsdigital.compositeadapter.utils.getCompositeAdapterCell
import com.originsdigital.compositeadapter.utils.getCompositeAdapterViewHolder
import com.originsdigital.compositeadapter.utils.setCompositeAdapterCell
import com.originsdigital.compositeadapter.utils.setCompositeAdapterViewHolder

private typealias TypedCell = Cell<Any?, RecyclerView.ViewHolder>

abstract class BaseCompositeAdapter<CELL : GenericCell>(
    config: AsyncDifferConfig<CELL>
) : ListAdapter<CELL, RecyclerView.ViewHolder>(config) {

    private val innerClickListener: View.OnClickListener = View.OnClickListener { view ->
        dispatchClick(holder = view.getCompositeAdapterViewHolder())
    }

    private lateinit var inflater: LayoutInflater
    private val typeInstances: SparseArray<GenericCell> = SparseArray()

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return item.viewType.also { viewType ->
            typeInstances.put(viewType, item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        val cell = typeInstances[viewType]
        return cell.onCreateViewHolder(inflater, parent, cell.viewType)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val cell = getCell(position)
        val isInternalPayloads = (payloads.isNotEmpty()
                && payloads.all { payload -> payload == Cell.CELL_INTERNAL_PAYLOAD })
        if (isInternalPayloads) {
            handleInternalPayload(holder, position, payloads, cell)
        } else if (!cell.onBindViewHolder(holder, position, payloads)) {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cell = getCell(position)
        storeHolderInView(holder, position, cell)
        storeCellInHolder(holder, position, cell)
        onBindClickListener(holder, position, cell)
        onBind(holder, position, cell)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        getCellFromHolder(holder).onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        getCellFromHolder(holder).onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        getCellFromHolder(holder).onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return getCellFromHolder(holder).onFailedToRecycleView(holder)
    }

    protected open fun handleInternalPayload(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>,
        cell: TypedCell
    ) {
        storeHolderInView(holder, position, cell)
        storeCellInHolder(holder, position, cell)
        onBindClickListener(holder, position, cell)
    }

    protected open fun storeHolderInView(
        holder: RecyclerView.ViewHolder,
        position: Int,
        cell: TypedCell
    ) {
        holder.setCompositeAdapterViewHolder()
    }

    protected open fun storeCellInHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        cell: TypedCell
    ) {
        holder.setCompositeAdapterCell(cell)
    }

    protected open fun onBindClickListener(
        holder: RecyclerView.ViewHolder,
        position: Int,
        cell: TypedCell
    ) {
        holder.itemView.setOnClickListener(innerClickListener)
        holder.itemView.isClickable = cell.onClickListener != null
    }

    protected open fun onBind(
        holder: RecyclerView.ViewHolder,
        position: Int,
        cell: TypedCell
    ) {
        cell.onBindViewHolder(holder, position)
    }

    protected open fun dispatchClick(holder: RecyclerView.ViewHolder) {
        val position = holder.bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            val cell = getCellFromHolder(holder)
            cell.onClickListener?.invoke(
                ClickItem(
                    holder = holder,
                    position = position,
                    item = cell.data
                )
            )
        }
    }

    protected fun getCell(position: Int): TypedCell {
        @Suppress("UNCHECKED_CAST")
        return getItem(position) as TypedCell
    }

    protected fun getCellFromHolder(holder: RecyclerView.ViewHolder): TypedCell {
        return holder.getCompositeAdapterCell()
    }
}