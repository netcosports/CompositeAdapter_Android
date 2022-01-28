/*
 * Copyright 2021 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.cell

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

typealias GenericClickItem<T> = ClickItem<T, RecyclerView.ViewHolder>

class ClickItem<out T, out VIEW_HOLDER : RecyclerView.ViewHolder>(
    val context: Context,
    val holder: VIEW_HOLDER,
    val position: Int,
    val item: T
)