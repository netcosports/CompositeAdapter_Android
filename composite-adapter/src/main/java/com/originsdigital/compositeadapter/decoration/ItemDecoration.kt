/*
 * Copyright 2021-2022 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.decoration

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.GenericCell

/**
 * An [ItemDecoration] used by the
 * [CompositeItemDecoration][com.originsdigital.compositeadapter.decoration.CompositeItemDecoration]
 * to add a special drawing and layout offset for only one
 * [Cell][com.originsdigital.compositeadapter.cell.Cell].
 *
 * @see [com.originsdigital.compositeadapter.cell.Cell]
 * @see [com.originsdigital.compositeadapter.decoration.CompositeItemDecoration]
 * @see [androidx.recyclerview.widget.RecyclerView.ItemDecoration]
 */
interface ItemDecoration {

    /**
     * Called by the [RecyclerView][androidx.recyclerview.widget.RecyclerView]
     * to get offsets for the given [view].
     *
     * @param view The [View][android.view.View] to decorate.
     * @param cell The [Cell][com.originsdigital.compositeadapter.cell.Cell]
     * attached to this [view].
     *
     * @see [androidx.recyclerview.widget.RecyclerView.ItemDecoration.getItemOffsets]
     */
    fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        cell: GenericCell
    ) = Unit

    /**
     * Called by the [RecyclerView][androidx.recyclerview.widget.RecyclerView]
     * to draw decorations under the [view].
     *
     * @param view The [View][android.view.View] to decorate.
     * @param cell The [Cell][com.originsdigital.compositeadapter.cell.Cell]
     * attached to this [view].
     *
     * @see [androidx.recyclerview.widget.RecyclerView.ItemDecoration.onDraw]
     */
    fun onDraw(
        canvas: Canvas,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        cell: GenericCell
    ) = Unit

    /**
     * Called by the [RecyclerView][androidx.recyclerview.widget.RecyclerView]
     * to draw decorations over the [view].
     *
     * @param view The [View][android.view.View] to decorate.
     * @param cell The [Cell][com.originsdigital.compositeadapter.cell.Cell]
     * attached to this [view].
     *
     * @see [androidx.recyclerview.widget.RecyclerView.ItemDecoration.onDrawOver]
     */
    fun onDrawOver(
        canvas: Canvas,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        cell: GenericCell
    ) = Unit
}