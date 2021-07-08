/*
 * Copyright 2021 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.originsdigital.compositeadapter.cell.Cell

open class CellItemCallback<CELL : Cell<*>> : DiffUtil.ItemCallback<CELL>() {

    override fun areItemsTheSame(oldItem: CELL, newItem: CELL): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: CELL, newItem: CELL): Boolean {
        return oldItem.areContentsTheSame(newItem) && oldItem.areDecorationsTheSame(newItem)
    }

    override fun getChangePayload(oldItem: CELL, newItem: CELL): Any? {
        val payload = oldItem.getChangePayload(newItem)
        return if (payload == null && oldItem.areContentsTheSame(newItem)) {
            Cell.CELL_DECORATION_PAYLOAD
        } else {
            payload
        }
    }
}