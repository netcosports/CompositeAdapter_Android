/*
 * Copyright 2021 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.composite.adapter.adapter

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import com.originsdigital.composite.adapter.cell.Cell
import com.originsdigital.composite.adapter.diffutil.CellItemCallback

open class CompositeAdapter(
    config: AsyncDifferConfig<Cell<*>> = getDefaultCellAsyncDifferConfig()
) : BaseCompositeAdapter<Cell<*>>(config) {

    companion object {
        @JvmStatic
        fun <DATA : Cell<*>> getDefaultCellAsyncDifferConfig(
            callback: DiffUtil.ItemCallback<DATA> = CellItemCallback()
        ): AsyncDifferConfig<DATA> {
            return AsyncDifferConfig.Builder(callback).build()
        }
    }
}