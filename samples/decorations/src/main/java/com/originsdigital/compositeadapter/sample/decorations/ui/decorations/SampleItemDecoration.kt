package com.originsdigital.compositeadapter.sample.decorations.ui.decorations

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.decoration.SpaceItemDecoration
import kotlin.math.roundToInt

data class SampleItemDecoration(
    private val type: Type,
    private val radius: Float,
    private val strokeWidth: Float,
    private val dividerHeight: Int,
    @ColorInt val dividerColorInt: Int,
    @ColorInt val backgroundColorInt: Int,
    override val top: Int = 0,
    override val bottom: Int = 0,
    override val start: Int = 0,
    override val end: Int = 0
) : SpaceItemDecoration<Cell<*>>() {

    private val dividerPaint = Paint().apply {
        color = dividerColorInt
    }
    private val backgroundPaint = Paint().apply {
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = this@SampleItemDecoration.strokeWidth
        color = backgroundColorInt
    }
    private val itemBounds = Rect()

    override fun onDraw(
        canvas: Canvas,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        item: Cell<*>
    ) {
        super.onDraw(canvas, view, parent, state, item)
        parent.layoutManager?.getDecoratedBoundsWithMargins(view, itemBounds)
        val translationY = view.translationY.roundToInt()
        itemBounds.bottom = itemBounds.bottom + translationY
        itemBounds.top = itemBounds.top + translationY
        val drawBottomDivider: Boolean
        val roundedTop: Boolean
        val roundedBottom: Boolean
        when (type) {
            Type.SINGLE -> {
                drawBottomDivider = false
                roundedTop = true
                roundedBottom = true
            }
            Type.TOP -> {
                drawBottomDivider = true
                roundedTop = true
                roundedBottom = false
            }
            Type.MIDDLE -> {
                drawBottomDivider = true
                roundedTop = false
                roundedBottom = false
            }
            Type.BOTTOM -> {
                drawBottomDivider = false
                roundedTop = false
                roundedBottom = true
            }
        }
        canvas.drawRoundedRect(
            paint = backgroundPaint,
            strokeWidth = strokeWidth / 2,
            left = itemBounds.left.toFloat() + start,
            top = itemBounds.top.toFloat() + top,
            right = itemBounds.right.toFloat() - end,
            bottom = itemBounds.bottom.toFloat() - bottom,
            radius = radius,
            withTop = roundedTop,
            withBottom = roundedBottom
        )
        if (drawBottomDivider) {
            itemBounds.left = itemBounds.left + start
            itemBounds.right = itemBounds.right - end
            itemBounds.top = itemBounds.bottom - dividerHeight
            canvas.drawRect(itemBounds, dividerPaint)
        }
    }

    private fun Canvas.drawRoundedRect(
        paint: Paint,
        strokeWidth: Float,
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        radius: Float,
        withTop: Boolean,
        withBottom: Boolean
    ) {
        save()
        clipRect(left, top, right, bottom)
        drawRoundRect(
            left + strokeWidth,
            if (withTop) top + strokeWidth else top - radius,
            right - strokeWidth,
            if (withBottom) bottom - strokeWidth else bottom + radius,
            radius,
            radius,
            paint
        )
        restore()
    }

    enum class Type {
        SINGLE, TOP, MIDDLE, BOTTOM
    }
}