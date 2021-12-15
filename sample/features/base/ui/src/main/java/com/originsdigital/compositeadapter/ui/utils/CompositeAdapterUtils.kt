package com.originsdigital.compositeadapter.ui.utils

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.originsdigital.compositeadapter.adapter.CompositeAdapter
import com.originsdigital.compositeadapter.cell.Cell

fun submitAdapterData(
    adapter: CompositeAdapter,
    cells: List<Cell<*>>,
    commitCallback: Runnable? = null
) {
    if (commitCallback == null) {
        adapter.submitList(cells)
    } else {
        adapter.submitList(cells, commitCallback)
    }
}

fun submitAdapterData(
    adapter: CompositeAdapter,
    swipeRefreshLayout: SwipeRefreshLayout,
    isRefreshing: Boolean,
    cells: List<Cell<*>>,
    commitCallback: Runnable? = null
) {
    swipeRefreshLayout.isRefreshing = isRefreshing
    if (commitCallback == null) {
        adapter.submitList(cells)
    } else {
        adapter.submitList(cells, commitCallback)
    }
}