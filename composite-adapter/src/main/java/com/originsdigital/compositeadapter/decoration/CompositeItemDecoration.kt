/*
 * Copyright 2021-2022 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.decoration

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.GenericCell
import com.originsdigital.compositeadapter.utils.getCompositeAdapterItem

open class CompositeItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        getItemOffsets(
            outRect = outRect,
            view = view,
            parent = parent,
            state = state,
            cell = getCellFromView(view)
        )

        if (parent.isRTL) {
            outRect.supportRTL()
        }
    }

    protected open fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        cell: GenericCell
    ) {
        cell.decoration?.getItemOffsets(
            outRect = outRect,
            view = view,
            parent = parent,
            state = state,
            cell = cell
        )
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        for (position in 0 until parent.childCount) {
            val child = parent.getChildAt(position)
            onDraw(
                canvas = c,
                view = child,
                parent = parent,
                state = state,
                cell = getCellFromView(child)
            )
        }
    }

    protected open fun onDraw(
        canvas: Canvas,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        cell: GenericCell
    ) {
        cell.decoration?.onDraw(
            canvas = canvas,
            view = view,
            parent = parent,
            state = state,
            cell = cell
        )
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        for (position in 0 until parent.childCount) {
            val child = parent.getChildAt(position)
            onDrawOver(
                canvas = c,
                view = child,
                parent = parent,
                state = state,
                cell = getCellFromView(child)
            )
        }
    }

    protected open fun onDrawOver(
        canvas: Canvas,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        cell: GenericCell
    ) {
        cell.decoration?.onDrawOver(
            canvas = canvas,
            view = view,
            parent = parent,
            state = state,
            cell = cell
        )
    }

    protected fun getCellFromView(view: View): GenericCell = view.getCompositeAdapterItem()

    protected val View.isRTL get() = context.resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL

    protected fun Rect.supportRTL() {
        val temp = left
        left = right
        right = temp
    }
}