/*
 * Copyright 2021 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.cell

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

class ClickItem<T>(
    val context: Context,
    val holder: RecyclerView.ViewHolder,
    val position: Int,
    val item: T
)