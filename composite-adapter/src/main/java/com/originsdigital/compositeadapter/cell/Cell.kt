/*
 * Copyright 2021-2022 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.cell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.decoration.ItemDecoration

typealias GenericCell = Cell<out Any?, out RecyclerView.ViewHolder>

/**
 * The [Cell] is an [Adapter][androidx.recyclerview.widget.RecyclerView.Adapter] item,
 * that handles all work with [ViewHolder][androidx.recyclerview.widget.RecyclerView.ViewHolder],
 * [ItemCallback][androidx.recyclerview.widget.DiffUtil.ItemCallback] and
 * [ItemDecoration][androidx.recyclerview.widget.RecyclerView.ItemDecoration].
 *
 * MUST NOT store any reference to the
 * [ViewHolder][androidx.recyclerview.widget.RecyclerView.ViewHolder], [View][android.view.View],
 * [Context][android.content.Context] or other Android component, that can be leaked.
 *
 * Can survive configuration changes inside [ViewModel][androidx.lifecycle.ViewModel]
 * or any other way.
 *
 * @see [com.originsdigital.compositeadapter.adapter.BaseCompositeAdapter]
 * @see [com.originsdigital.compositeadapter.diffutil.CellItemCallback]
 */
interface Cell<T, VIEW_HOLDER : RecyclerView.ViewHolder> {

    /**
     * The id of the [Cell]. Must be unique, but only for the same [viewType]
     *
     * Used by the [areItemsTheSame] to check whether two [cells][Cell] represent the same item.
     */
    val uniqueId: String

    /**
     * The data to bind in the [onBindViewHolder].
     *
     * Used by the [areContentsTheSame] to check whether two [cells][Cell] have the same [data].
     *
     * Used by the [com.originsdigital.compositeadapter.adapter.BaseCompositeAdapter.dispatchClick]
     * to create a [ClickItem].
     */
    val data: T


    /**
     * The view type of the [Cell]. Must be unique among all different [cells][Cell].
     *
     * Can be generated via ids.xml.
     *
     * Used by the [areItemsTheSame] to check whether two objects represent the same item.
     *
     * Used by the [androidx.recyclerview.widget.RecyclerView.Adapter]
     * for the purposes of view recycling.
     */
    val viewType: Int


    /**
     * The decoration for this [Cell] only.
     *
     * Used by the [com.originsdigital.compositeadapter.decoration.CompositeItemDecoration]
     * to add a special drawing and layout offset for this [Cell] only.
     */
    val decoration: ItemDecoration? get() = null


    /**
     * The root view click listener of the [Cell].
     * Called to dispatch [ClickItem][com.originsdigital.compositeadapter.cell.ClickItem].
     *
     * Used by the [com.originsdigital.compositeadapter.adapter.BaseCompositeAdapter.dispatchClick]
     */
    val onClickListener: ((ClickItem<T, VIEW_HOLDER>) -> Unit)? get() = null


    /**
     * Called when [RecyclerView][androidx.recyclerview.widget.RecyclerView]
     * needs a new [VIEW_HOLDER] to represent this [Cell].
     *
     * @see [androidx.recyclerview.widget.RecyclerView.Adapter.onCreateViewHolder]
     */
    fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): VIEW_HOLDER

    /**
     * Called by the [RecyclerView][androidx.recyclerview.widget.RecyclerView] to display the [data] with [payloads]
     *
     * @see [androidx.recyclerview.widget.RecyclerView.Adapter.onBindViewHolder]
     */
    fun onBindViewHolder(holder: VIEW_HOLDER, position: Int, payloads: List<Any>): Boolean = false

    /**
     * Called by the [RecyclerView][androidx.recyclerview.widget.RecyclerView] to display the [data]
     *
     * @see [androidx.recyclerview.widget.RecyclerView.Adapter.onBindViewHolder]
     */
    fun onBindViewHolder(holder: VIEW_HOLDER, position: Int)

    /**
     * Called when a [VIEW_HOLDER] attached to this [Cell] has been attached to a window.
     *
     * @see [androidx.recyclerview.widget.RecyclerView.Adapter.onViewAttachedToWindow]
     */
    fun onViewAttachedToWindow(holder: VIEW_HOLDER) = Unit

    /**
     * Called when a [VIEW_HOLDER] attached to this [Cell] has been detached from its window.
     *
     * @see [androidx.recyclerview.widget.RecyclerView.Adapter.onViewDetachedFromWindow]
     */
    fun onViewDetachedFromWindow(holder: VIEW_HOLDER) = Unit

    /**
     * Called when a [VIEW_HOLDER] attached to this [Cell] has been recycled.
     *
     * @see [androidx.recyclerview.widget.RecyclerView.Adapter.onViewRecycled]
     */
    fun onViewRecycled(holder: VIEW_HOLDER) = Unit

    /**
     * Called by the [RecyclerView][androidx.recyclerview.widget.RecyclerView] if a [VIEW_HOLDER]
     * created by this [Cell] cannot be recycled due to its transient state.
     *
     * Default implementation returns false.
     *
     * @see [androidx.recyclerview.widget.RecyclerView.Adapter.onFailedToRecycleView]
     */
    fun onFailedToRecycleView(holder: VIEW_HOLDER): Boolean = false


    /**
     * Called to check whether two [cells][Cell] represent the same item.
     *
     * Default implementation compares [viewType] and [uniqueId].
     *
     * Used by the [com.originsdigital.compositeadapter.diffutil.CellItemCallback.areItemsTheSame]
     *
     * @see [androidx.recyclerview.widget.DiffUtil.ItemCallback.areItemsTheSame]
     */
    fun areItemsTheSame(newItem: GenericCell): Boolean {
        return viewType == newItem.viewType && uniqueId == newItem.uniqueId
    }

    /**
     * Called to check whether two [cells][Cell] have the same [data].
     *
     * Default implementation compares [data].
     *
     * Used by the [com.originsdigital.compositeadapter.diffutil.CellItemCallback.areContentsTheSame]
     *
     * @see [androidx.recyclerview.widget.DiffUtil.ItemCallback.areContentsTheSame]
     */
    fun areContentsTheSame(newItem: GenericCell): Boolean = data == newItem.data

    /**
     * Called to check whether two [cells][Cell] have the same [decoration].
     *
     * Default implementation compares [decoration].
     *
     * Used by the [com.originsdigital.compositeadapter.diffutil.CellItemCallback.areContentsTheSame]
     */
    fun areDecorationsTheSame(newItem: GenericCell): Boolean = decoration == newItem.decoration

    /**
     * Called to check whether two [cells][Cell] have the same [onClickListener].
     *
     * Default implementation compares [onClickListener].
     *
     * Used by the [com.originsdigital.compositeadapter.diffutil.CellItemCallback.areContentsTheSame]
     */
    fun areOnClickListenersTheSame(newItem: GenericCell): Boolean {
        return onClickListener == newItem.onClickListener
    }

    /**
     * When [areItemsTheSame] returns true for two [cells][Cell]
     * and [areContentsTheSame] returns false for them,
     * this method is called to get a payload about the change.
     *
     * Default implementation returns null.
     *
     * Used by the [com.originsdigital.compositeadapter.diffutil.CellItemCallback.getChangePayload]
     *
     * @see [androidx.recyclerview.widget.DiffUtil.ItemCallback.getChangePayload]
     */
    fun getChangePayload(newItem: GenericCell): Any? = null

    companion object {
        /**
         * The payload indicating that the [decoration] or [onClickListener] has changed,
         * but not the [data].
         *
         * Used by the [com.originsdigital.compositeadapter.adapter.BaseCompositeAdapter.onBindViewHolder]
         *
         * Used by the [com.originsdigital.compositeadapter.diffutil.CellItemCallback.getChangePayload]
         *
         * @see [androidx.recyclerview.widget.DiffUtil.ItemCallback.getChangePayload]
         */
        const val CELL_INTERNAL_PAYLOAD = "CELL_INTERNAL_PAYLOAD"
    }
}