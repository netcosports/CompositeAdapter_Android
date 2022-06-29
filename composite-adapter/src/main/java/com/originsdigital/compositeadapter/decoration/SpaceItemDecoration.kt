/*
 * Copyright 2021-2022 Origins Digital. Use of this source code is governed by the Apache 2.0 license.
 */

package com.originsdigital.compositeadapter.decoration

/**
 * [SpaceItemDecoration] is used by the
 * [CompositeItemDecoration][com.originsdigital.compositeadapter.decoration.CompositeItemDecoration]
 * to add a special layout offset for only one
 * [Cell][com.originsdigital.compositeadapter.cell.Cell].
 *
 * @see [com.originsdigital.compositeadapter.decoration.ItemDecoration]
 * @see [com.originsdigital.compositeadapter.decoration.CompositeItemDecoration]
 * @see [com.originsdigital.compositeadapter.cell.Cell]
 * @see [androidx.recyclerview.widget.RecyclerView.ItemDecoration]
 */
data class SpaceItemDecoration(
    override val top: Int = 0,
    override val bottom: Int = 0,
    override val start: Int = 0,
    override val end: Int = 0
) : BaseSpaceItemDecoration()