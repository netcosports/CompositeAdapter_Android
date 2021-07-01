package com.netcosports.components.adapter.recycler.decoration

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.netcosports.components.adapter.recycler.cell.Cell

open class CompositeItemDecoration : BaseCompositeItemDecoration<Cell<*>>() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        item: Cell<*>
    ) {
        getDecoration(item)?.getItemOffsets(outRect, view, parent, state, item)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val viewsCount = parent.childCount
        for (position in 0 until viewsCount) {
            val child = parent.getChildAt(position)

            onDraw(c, child, parent, state, getItemFromView(child))
        }
    }

    protected open fun onDraw(
        canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State, item: Cell<*>
    ) {
        getDecoration(item)?.onDraw(canvas, view, parent, state, item)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val viewsCount = parent.childCount
        for (position in 0 until viewsCount) {
            val child = parent.getChildAt(position)

            onDrawOver(c, child, parent, state, getItemFromView(child))
        }
    }

    protected open fun onDrawOver(
        canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State, item: Cell<*>
    ) {
        getDecoration(item)?.onDrawOver(canvas, view, parent, state, item)
    }

    protected fun getDecoration(cell: Cell<*>): ItemDecoration<Cell<*>>? {
        @Suppress("UNCHECKED_CAST")
        return cell.decoration as ItemDecoration<Cell<*>>?
    }
}