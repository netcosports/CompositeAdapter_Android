/*
 * Copyright 2021-2022 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.decoration

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.GenericCell
import com.originsdigital.compositeadapter.utils.getCompositeAdapterCell

/**
 * [CompositeItemDecoration] is used to delegate all requests of the
 * [ItemDecoration][androidx.recyclerview.widget.RecyclerView.ItemDecoration]
 * to the [decoration][com.originsdigital.compositeadapter.cell.Cell.decoration]
 * field of the [Cell][com.originsdigital.compositeadapter.cell.Cell]
 *
 * @see com.originsdigital.compositeadapter.decoration.ItemDecoration
 * @see androidx.recyclerview.widget.RecyclerView.ItemDecoration
 */
open class CompositeItemDecoration : RecyclerView.ItemDecoration() {

    /**
     * Retrieves a [Cell][com.originsdigital.compositeadapter.cell.Cell]
     * from the given [view] and passes it to the [getItemOffsets].
     *
     * Adds RTL support for offsets.
     *
     * @see [androidx.recyclerview.widget.RecyclerView.ItemDecoration.getItemOffsets]
     */
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

    /**
     * Delegates this request to the
     * [decoration][com.originsdigital.compositeadapter.cell.Cell.decoration] field
     * of the [Cell][com.originsdigital.compositeadapter.cell.Cell] attached to the given [view].
     *
     * @see [com.originsdigital.compositeadapter.decoration.ItemDecoration.getItemOffsets]
     * @see [androidx.recyclerview.widget.RecyclerView.ItemDecoration.getItemOffsets]
     */
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

    /**
     * Retrieves a [Cell][com.originsdigital.compositeadapter.cell.Cell]
     * from the each [view] of the given [parent] and passes it to the [onDraw].
     *
     * @see [androidx.recyclerview.widget.RecyclerView.ItemDecoration.onDraw]
     */
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

    /**
     * Delegates this request to the
     * [decoration][com.originsdigital.compositeadapter.cell.Cell.decoration] field
     * of the [Cell][com.originsdigital.compositeadapter.cell.Cell] attached to the given [view].
     *
     * @see [com.originsdigital.compositeadapter.decoration.ItemDecoration.onDraw]
     * @see [androidx.recyclerview.widget.RecyclerView.ItemDecoration.onDraw]
     */
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

    /**
     * Retrieves a [Cell][com.originsdigital.compositeadapter.cell.Cell]
     * from the each [view] of the given [parent] and passes it to the [onDrawOver].
     *
     * @see [androidx.recyclerview.widget.RecyclerView.ItemDecoration.onDrawOver]
     */
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

    /**
     * Delegates this request to the
     * [decoration][com.originsdigital.compositeadapter.cell.Cell.decoration] field
     * of the [Cell][com.originsdigital.compositeadapter.cell.Cell] attached to the given [view].
     *
     * @see [com.originsdigital.compositeadapter.decoration.ItemDecoration.onDrawOver]
     * @see [androidx.recyclerview.widget.RecyclerView.ItemDecoration.onDrawOver]
     */
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

    /** @see com.originsdigital.compositeadapter.utils.getCompositeAdapterCell */
    protected fun getCellFromView(view: View): GenericCell = view.getCompositeAdapterCell()

    /**
     * @return true if the orientation is RTL and false otherwise.
     */
    protected val View.isRTL get() = context.resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL

    /**
     * Adds RTL support for the
     * [left][android.graphics.Rect.left] and [right][android.graphics.Rect.right] fields.
     *
     * @receiver The [Rect][android.graphics.Rect] to add RTL support.
     */
    protected fun Rect.supportRTL() {
        val temp = left
        left = right
        right = temp
    }
}