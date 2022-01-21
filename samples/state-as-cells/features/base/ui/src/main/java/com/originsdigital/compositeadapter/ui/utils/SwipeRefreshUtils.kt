package com.originsdigital.compositeadapter.ui.utils

import android.graphics.Color
import androidx.core.content.res.use
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.originsdigital.compositeadapter.ui.R

fun initSwipeRefresh(
    swipeRefreshLayout: SwipeRefreshLayout,
    onRefresh: () -> Unit
) {
    val schemeColor = swipeRefreshLayout.context.obtainStyledAttributes(
        intArrayOf(R.attr.colorSecondary)
    ).use {
        it.getColor(0, Color.BLACK)
    }
    swipeRefreshLayout.setColorSchemeColors(schemeColor, schemeColor, schemeColor)
    swipeRefreshLayout.setOnRefreshListener { onRefresh() }
}

fun setSwipeEnabled(
    swipeRefreshLayout: SwipeRefreshLayout,
    isEnabled: Boolean
) {
    if (!isEnabled) {
        swipeRefreshLayout.isRefreshing = false
    }
    swipeRefreshLayout.isEnabled = isEnabled
}