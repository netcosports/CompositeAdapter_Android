package com.originsdigital.compositeadapter.ui.utils

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.originsdigital.compositeadapter.adapter.CompositeAdapter
import com.originsdigital.compositeadapter.cell.GenericCell

fun submitAdapterData(
    adapter: CompositeAdapter,
    cells: List<GenericCell>,
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
    cells: List<GenericCell>,
    commitCallback: Runnable? = null
) {
    swipeRefreshLayout.isRefreshing = isRefreshing
    submitAdapterData(
        adapter = adapter,
        cells = cells,
        commitCallback = commitCallback
    )
}