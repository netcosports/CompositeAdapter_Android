/*
 * Copyright 2021 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.composite.adapter.decoration

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface ItemDecoration<Item> {

    fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State, item: Item) = Unit

    fun onDraw(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State, item: Item) = Unit

    fun onDrawOver(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State, item: Item) = Unit
}