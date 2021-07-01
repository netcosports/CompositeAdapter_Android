package com.netcosports.composite.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.netcosports.composite.adapter.cell.Cell

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