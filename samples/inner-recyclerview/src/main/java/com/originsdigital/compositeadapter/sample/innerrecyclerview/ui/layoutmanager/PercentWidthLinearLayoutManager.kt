package com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.layoutmanager

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PercentWidthLinearLayoutManager(
    context: Context
) : LinearLayoutManager(context, HORIZONTAL, false) {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return super.generateDefaultLayoutParams().scale()
    }

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams?): RecyclerView.LayoutParams {
        return super.generateLayoutParams(lp).scale()
    }

    override fun generateLayoutParams(
        c: Context?,
        attrs: AttributeSet?
    ): RecyclerView.LayoutParams {
        return super.generateLayoutParams(c, attrs).scale()
    }

    private fun RecyclerView.LayoutParams.scale(): RecyclerView.LayoutParams {
        return this.apply {
            width = calculateChildWidth(getWidth() - paddingStart - paddingEnd)
        }
    }

    companion object {
        fun calculateChildWidth(parentWidth: Int): Int = parentWidth / 3
    }
}