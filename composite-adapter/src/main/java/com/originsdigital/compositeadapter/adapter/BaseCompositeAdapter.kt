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

/**
 * A [RecyclerView Adapter][androidx.recyclerview.widget.RecyclerView.Adapter],
 * that delegates all work with [ViewHolder][androidx.recyclerview.widget.RecyclerView.ViewHolder]
 * to the [cells][com.originsdigital.compositeadapter.cell.Cell].
 *
 * @see [com.originsdigital.compositeadapter.cell.Cell]
 *
 * @see [androidx.recyclerview.widget.RecyclerView.Adapter]
 */
abstract class BaseCompositeAdapter<CELL : GenericCell>(
    config: AsyncDifferConfig<CELL>
) : ListAdapter<CELL, RecyclerView.ViewHolder>(config) {

    /**
     * Listener used to dispatch clicks on the
     * [itemView][androidx.recyclerview.widget.RecyclerView.ViewHolder.itemView] of the
     * [ViewHolder][androidx.recyclerview.widget.RecyclerView.ViewHolder] to the attached
     * [Cell][com.originsdigital.compositeadapter.cell.Cell].
     *
     * @see [onBindClickListener]
     * @see [dispatchClick]
     */
    private val innerClickListener: View.OnClickListener = View.OnClickListener { view ->
        dispatchClick(holder = view.getCompositeAdapterViewHolder())
    }

    private lateinit var inflater: LayoutInflater
    private val typeInstances: SparseArray<CELL> = SparseArray()

    /**
     * Delegates the [getItemViewType] request to the
     * [Cell][com.originsdigital.compositeadapter.cell.Cell] at the given [position].
     *
     * Stores this [Cell][com.originsdigital.compositeadapter.cell.Cell] in the [typeInstances]
     * for the purposes of [onCreateViewHolder].
     */
    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return item.viewType.also { viewType ->
            typeInstances.put(viewType, item)
        }
    }

    /**
     * Delegates the [onCreateViewHolder] request to the
     * [Cell][com.originsdigital.compositeadapter.cell.Cell] of the given [viewType].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        val cell = typeInstances[viewType]
        return cell.onCreateViewHolder(inflater, parent, cell.viewType)
    }

    /**
     * Processes the
     * [CELL_INTERNAL_PAYLOAD][com.originsdigital.compositeadapter.cell.Cell.CELL_INTERNAL_PAYLOAD]
     * if it passed, and delegates the [onBindViewHolder] request to the
     * [Cell][com.originsdigital.compositeadapter.cell.Cell] at the given [position] if there were
     * additional payloads.
     */
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val cell = getCell(position)
        val isAnyInternalPayload = payloads.any { payload ->
            payload == Cell.CELL_INTERNAL_PAYLOAD
        }
        val isOnlyInternalPayloads = (payloads.isNotEmpty() && payloads.all { payload ->
            payload == Cell.CELL_INTERNAL_PAYLOAD
        })
        if (isAnyInternalPayload) { // handle internal payloads before the Cell
            handleInternalPayload(holder, position, payloads, cell)
        }
        if (!isOnlyInternalPayloads && !cell.onBindViewHolder(holder, position, payloads)) {
            // Cell didn't process the additional payloads, full onBindViewHolder should be called
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cell = getCell(position)
        storeHolderInView(holder, position, cell)
        storeCellInHolder(holder, position, cell)
        onBindClickListener(holder, position, cell)
        onBindViewHolder(holder, position, cell)
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

    /**
     * Stores the given [holder] in the
     * [itemView][androidx.recyclerview.widget.RecyclerView.ViewHolder.itemView] for the purposes of
     * [innerClickListener].
     */
    protected open fun storeHolderInView(
        holder: RecyclerView.ViewHolder,
        position: Int,
        cell: TypedCell
    ) {
        holder.setCompositeAdapterViewHolder()
    }

    /**
     * Stores the given [cell] in the given [holder]
     *
     * @see [getCellFromHolder]
     */
    protected open fun storeCellInHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        cell: TypedCell
    ) {
        holder.setCompositeAdapterCell(cell)
    }

    /**
     * Sets the [innerClickListener] to the
     * [itemView][androidx.recyclerview.widget.RecyclerView.ViewHolder.itemView] of the given
     * [ViewHolder][androidx.recyclerview.widget.RecyclerView.ViewHolder]
     */
    protected open fun onBindClickListener(
        holder: RecyclerView.ViewHolder,
        position: Int,
        cell: TypedCell
    ) {
        holder.itemView.setOnClickListener(innerClickListener)
        holder.itemView.isClickable = cell.onClickListener != null
    }

    /**
     * Delegates the [onBindViewHolder] request to the given
     * [Cell][com.originsdigital.compositeadapter.cell.Cell].
     */
    protected open fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        cell: TypedCell
    ) {
        cell.onBindViewHolder(holder, position)
    }

    /**
     * Delegates the [onViewAttachedToWindow] request to the current
     * [Cell][com.originsdigital.compositeadapter.cell.Cell] attached to the given [holder].
     */
    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        getCellFromHolder(holder).onViewAttachedToWindow(holder)
    }

    /**
     * Delegates the [onViewDetachedFromWindow] request to the current
     * [Cell][com.originsdigital.compositeadapter.cell.Cell] attached to the given [holder].
     */
    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        getCellFromHolder(holder).onViewDetachedFromWindow(holder)
    }

    /**
     * Delegates the [onViewRecycled] request to the current
     * [Cell][com.originsdigital.compositeadapter.cell.Cell] attached to the given [holder].
     */
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        getCellFromHolder(holder).onViewRecycled(holder)
    }

    /**
     * Delegates the [onFailedToRecycleView] request to the current
     * [Cell][com.originsdigital.compositeadapter.cell.Cell] attached to the given [holder].
     */
    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return getCellFromHolder(holder).onFailedToRecycleView(holder)
    }

    /**
     * Dispatches clicks on the
     * [itemView][androidx.recyclerview.widget.RecyclerView.ViewHolder.itemView] of the given
     * [ViewHolder][androidx.recyclerview.widget.RecyclerView.ViewHolder] to the attached
     * [Cell][com.originsdigital.compositeadapter.cell.Cell].
     */
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