/*
 * Copyright 2021-2022 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.cell

import androidx.recyclerview.widget.RecyclerView

typealias GenericClickItem<T> = ClickItem<T, RecyclerView.ViewHolder>

/**
 * [ClickItem] is used to dispatch [holder], [position] and [item] of the clicked [Cell].
 *
 * Used by the [com.originsdigital.compositeadapter.cell.Cell.onClickListener]
 *
 * Used by the [com.originsdigital.compositeadapter.adapter.BaseCompositeAdapter.dispatchClick]
 */
class ClickItem<out T, out VIEW_HOLDER : RecyclerView.ViewHolder>(
    val holder: VIEW_HOLDER,
    val position: Int,
    val item: T
)