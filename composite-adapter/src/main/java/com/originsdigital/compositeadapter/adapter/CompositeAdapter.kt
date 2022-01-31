/*
 * Copyright 2021-2022 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.adapter

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import com.originsdigital.compositeadapter.cell.GenericCell
import com.originsdigital.compositeadapter.diffutil.CellItemCallback

open class CompositeAdapter(
    config: AsyncDifferConfig<GenericCell> = getDefaultCellAsyncDifferConfig()
) : BaseCompositeAdapter<GenericCell>(config) {

    companion object {
        @JvmStatic
        fun <DATA : GenericCell> getDefaultCellAsyncDifferConfig(
            callback: DiffUtil.ItemCallback<DATA> = CellItemCallback()
        ): AsyncDifferConfig<DATA> {
            return AsyncDifferConfig.Builder(callback).build()
        }
    }
}