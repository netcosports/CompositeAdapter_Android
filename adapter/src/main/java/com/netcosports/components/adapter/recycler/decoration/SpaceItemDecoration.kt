package com.netcosports.components.adapter.recycler.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class SpaceItemDecoration<CELL>(
    val top: Int = 0,
    val bottom: Int = 0,
    val start: Int = 0,
    val end: Int = 0
) : ItemDecoration<CELL> {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State, item: CELL) {
        outRect.left = start
        outRect.top = top
        outRect.right = end
        outRect.bottom = bottom
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SpaceItemDecoration<*>

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