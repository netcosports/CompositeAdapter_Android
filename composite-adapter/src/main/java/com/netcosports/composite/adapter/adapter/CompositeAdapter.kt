package com.netcosports.composite.adapter.adapter

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import com.netcosports.composite.adapter.cell.Cell
import com.netcosports.composite.adapter.diffutil.CellItemCallback

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