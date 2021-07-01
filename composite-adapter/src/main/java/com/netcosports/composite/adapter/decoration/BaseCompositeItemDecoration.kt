package com.netcosports.composite.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.netcosports.composite.adapter.utils.getCompositeAdapterItem

abstract class BaseCompositeItemDecoration<ITEM> : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        getItemOffsets(outRect, view, parent, state, getItemFromView(view))

        if (parent.isRTL) {
            outRect.supportRTL()
        }
    }

    protected open fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        item: ITEM
    ) = Unit

    protected fun getItemFromView(view: View): ITEM = view.getCompositeAdapterItem()

    protected val View.isRTL get() = context.resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL

    protected fun Rect.supportRTL() {
        val temp = left
        left = right
        right = temp
    }

    protected fun View.getRTL(value: Int, mirrored: Int): Int = if (isRTL) mirrored else value
}