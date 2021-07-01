package com.netcosports.components.adapter.recycler.decoration

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface ItemDecoration<Item> {

    fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State, item: Item) = Unit

    fun onDraw(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State, item: Item) = Unit

    fun onDrawOver(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State, item: Item) = Unit
}