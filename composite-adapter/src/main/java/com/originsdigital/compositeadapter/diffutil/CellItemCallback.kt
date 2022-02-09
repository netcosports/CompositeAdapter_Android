/*
 * Copyright 2021-2022 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.cell.GenericCell

/**
 * Callback for calculating the diff between two non-null
 * [cells][com.originsdigital.compositeadapter.cell.Cell].
 *
 * @see androidx.recyclerview.widget.DiffUtil.ItemCallback
 */
open class CellItemCallback<CELL : GenericCell> : DiffUtil.ItemCallback<CELL>() {

    /**
     * Called to check whether two [cells][com.originsdigital.compositeadapter.cell.Cell]
     * represent the same item.
     *
     * @see [com.originsdigital.compositeadapter.cell.Cell.areItemsTheSame]
     *
     * @see [androidx.recyclerview.widget.DiffUtil.ItemCallback.areItemsTheSame]
     */
    override fun areItemsTheSame(oldItem: CELL, newItem: CELL): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    /**
     * Called to check whether two [cells][com.originsdigital.compositeadapter.cell.Cell]
     * have the same [com.originsdigital.compositeadapter.cell.Cell.data].
     *
     * @see [com.originsdigital.compositeadapter.cell.Cell.areContentsTheSame]
     * @see [com.originsdigital.compositeadapter.cell.Cell.areDecorationsTheSame]
     * @see [com.originsdigital.compositeadapter.cell.Cell.areOnClickListenersTheSame]
     *
     * @see [androidx.recyclerview.widget.DiffUtil.ItemCallback.areContentsTheSame]
     */
    override fun areContentsTheSame(oldItem: CELL, newItem: CELL): Boolean {
        return oldItem.areContentsTheSame(newItem)
                && oldItem.areDecorationsTheSame(newItem)
                && oldItem.areOnClickListenersTheSame(newItem)
    }

    /**
     * When [areItemsTheSame] returns true for two [cells][com.originsdigital.compositeadapter.cell.Cell]
     * and [areContentsTheSame] returns false for them,
     * this method is called to get a payload about the change.
     *
     * @return [com.originsdigital.compositeadapter.cell.Cell.CELL_INTERNAL_PAYLOAD]
     * if the [com.originsdigital.compositeadapter.cell.Cell.areContentsTheSame] returns false
     * or [com.originsdigital.compositeadapter.cell.Cell.getChangePayload] otherwise.
     *
     * @see [com.originsdigital.compositeadapter.cell.Cell.getChangePayload]
     *
     * @see [androidx.recyclerview.widget.DiffUtil.ItemCallback.getChangePayload]
     */
    override fun getChangePayload(oldItem: CELL, newItem: CELL): Any? {
        return if (oldItem.areContentsTheSame(newItem)) {
            // The content hasn't changed, but the decoration or onClickListener has.
            Cell.CELL_INTERNAL_PAYLOAD
        } else {
            oldItem.getChangePayload(newItem)
        }
    }
}