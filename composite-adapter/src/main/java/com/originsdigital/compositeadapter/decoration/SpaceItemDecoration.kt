/*
 * Copyright 2021-2022 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.GenericCell

/**
 * [SpaceItemDecoration] used by the
 * [CompositeItemDecoration][com.originsdigital.compositeadapter.decoration.CompositeItemDecoration]
 * to add a special drawing and layout offset for only one
 * [Cell][com.originsdigital.compositeadapter.cell.Cell].
 *
 * @see [com.originsdigital.compositeadapter.decoration.ItemDecoration]
 * @see [com.originsdigital.compositeadapter.decoration.CompositeItemDecoration]
 * @see [com.originsdigital.compositeadapter.cell.Cell]
 * @see [androidx.recyclerview.widget.RecyclerView.ItemDecoration]
 */
open class SpaceItemDecoration(
    open val top: Int = 0,
    open val bottom: Int = 0,
    open val start: Int = 0,
    open val end: Int = 0
) : ItemDecoration {

    /** @see [com.originsdigital.compositeadapter.decoration.ItemDecoration.getItemOffsets] */
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        cell: GenericCell
    ) {
        outRect.left = start
        outRect.top = top
        outRect.right = end
        outRect.bottom = bottom
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SpaceItemDecoration

        if (top != other.top) return false
        if (bottom != other.bottom) return false
        if (start != other.start) return false
        if (end != other.end) return false

        return true
    }

    override fun hashCode(): Int {
        var result = top
        result = 31 * result + bottom
        result = 31 * result + start
        result = 31 * result + end
        return result
    }
}