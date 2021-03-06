/*
 * Copyright 2021-2022 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.GenericCell

/**
 * [BaseSpaceItemDecoration] is used by the
 * [CompositeItemDecoration][com.originsdigital.compositeadapter.decoration.CompositeItemDecoration]
 * to add a special drawing and layout offset for only one
 * [Cell][com.originsdigital.compositeadapter.cell.Cell].
 *
 * @see [com.originsdigital.compositeadapter.decoration.ItemDecoration]
 * @see [com.originsdigital.compositeadapter.decoration.CompositeItemDecoration]
 * @see [com.originsdigital.compositeadapter.cell.Cell]
 * @see [androidx.recyclerview.widget.RecyclerView.ItemDecoration]
 */
abstract class BaseSpaceItemDecoration : ItemDecoration {

    abstract val top: Int
    abstract val bottom: Int
    abstract val start: Int
    abstract val end: Int

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
}