package com.netcosports.composite.adapter.sample.ui.sample

import android.app.Application
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.netcosports.composite.adapter.cell.Cell
import com.netcosports.composite.adapter.cell.ClickItem
import com.netcosports.composite.adapter.decoration.SpaceItemDecoration
import com.netcosports.composite.adapter.sample.domain.entity.MessageEntity
import com.netcosports.composite.adapter.sample.ui.sample.cell.common.SampleErrorCell
import com.netcosports.composite.adapter.sample.ui.sample.cell.common.SampleFullScreenErrorCell
import com.netcosports.composite.adapter.sample.ui.sample.cell.common.SampleFullScreenLoaderCell
import com.netcosports.composite.adapter.sample.ui.sample.cell.common.SampleLoaderCell
import com.netcosports.composite.adapter.sample.ui.sample.cell.databinding.SampleDataBindingMessageCell
import com.netcosports.composite.adapter.sample.ui.sample.cell.view.SampleViewMessageCell
import com.netcosports.composite.adapter.sample.ui.sample.cell.viewbinding.SampleViewBindingMessageCell
import java.util.*

class SampleMapper(
    app: Application,
    private val itemsPerPage: Int,
    private val viewMessageClickListener: (ClickItem<MessageEntity>) -> Unit,
    private val viewBindingMessageClickListener: (ClickItem<MessageEntity>) -> Unit,
    private val dataBindingMessageClickListener: (ClickItem<MessageEntity>) -> Unit,
) {

    private val firstItemDecoration: SpaceItemDecoration<Cell<*>>
    private val middleSmallItemDecoration: SpaceItemDecoration<Cell<*>>
    private val middleBigItemDecoration: SpaceItemDecoration<Cell<*>>
    private val lastItemDecoration: SpaceItemDecoration<Cell<*>>

    init {
        val space = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, app.resources.displayMetrics).toInt()
        firstItemDecoration = SpaceItemDecoration(top = 8 * space, bottom = space, start = 8 * space, end = 8 * space)
        middleSmallItemDecoration = object : SpaceItemDecoration<Cell<*>>(
            top = 2 * space, bottom = 2 * space, start = 4 * space, end = 4 * space
        ) {
            private val itemBounds = Rect()
            private val dividerPaint: Paint = Paint().apply {
                this.color = Color.GREEN
                this.style = Paint.Style.FILL
                this.isAntiAlias = true
            }

            override fun onDraw(canvas: Canvas, view: View, parent: RecyclerView, state: RecyclerView.State, item: Cell<*>) {
                super.onDraw(canvas, view, parent, state, item)
                parent.layoutManager?.getDecoratedBoundsWithMargins(view, itemBounds)
                canvas.drawRect(
                    itemBounds.left.toFloat(),
                    itemBounds.top.toFloat(),
                    itemBounds.right.toFloat(),
                    itemBounds.bottom.toFloat(),
                    dividerPaint
                )
            }
        }
        middleBigItemDecoration = SpaceItemDecoration(top = space, bottom = space, start = space, end = space)
        lastItemDecoration = SpaceItemDecoration(top = space, bottom = 8 * space, start = 8 * space, end = 8 * space)
    }

    fun mapToCells(messages: List<MessageEntity>, extraCell: Cell<*>? = null): List<Cell<*>> {
        val result = mutableListOf<Cell<*>>()

        messages.mapIndexedTo(result) { i, message ->
            val decoration = when {
                i == 0 -> firstItemDecoration
                i == messages.size - 1 -> lastItemDecoration
                else -> {
                    if (i % ((itemsPerPage + messages.size) / itemsPerPage) == 0) {
                        middleSmallItemDecoration
                    } else {
                        middleBigItemDecoration
                    }
                }
            }
            when (message.type) {
                MessageEntity.Type.VIEW -> SampleViewMessageCell(
                    message.copy(text = "ViewMessageCell ${message.text}"),
                    decoration = decoration,
                    onClickListener = viewMessageClickListener
                )
                MessageEntity.Type.VIEW_BINDING -> SampleViewBindingMessageCell(
                    message.copy(text = "ViewBindingMessageCell ${message.text}"),
                    decoration = decoration,
                    onClickListener = viewBindingMessageClickListener
                )
                MessageEntity.Type.DATA_BINDING -> SampleDataBindingMessageCell(
                    message.copy(text = "DataBindingMessageCell ${message.text}"),
                    decoration = decoration,
                    onClickListener = dataBindingMessageClickListener
                )
            }
        }
        if (extraCell != null) {
            result.add(extraCell)
        }

        return result
    }

    fun changeMessageType(messages: List<MessageEntity>, input: MessageEntity): List<MessageEntity> {
        return messages.map { message ->
            if (input.id == message.id) {
                message.copy(type = MessageEntity.Type.values().toList().minus(input.type).random())
            } else {
                message
            }
        }
    }

    fun getErrorMessage(random: Random): String {
        val diff = 'z' - 'a'
        val errorMessage = (0..5).map {
            ('a' + random.nextInt(diff)).let { char ->
                if (random.nextBoolean()) {
                    char
                } else {
                    char.uppercase()
                }
            }
        }.joinToString("")
        return "Error $errorMessage"
    }

    fun getLoaderCell(fullscreen: Boolean): Cell<*> {
        return if (fullscreen) {
            SampleFullScreenLoaderCell()
        } else {
            SampleLoaderCell()
        }
    }

    fun getErrorCell(error: String, fullscreen: Boolean, onRetryClickListener: () -> Unit): Cell<*> {
        return if (fullscreen) {
            SampleFullScreenErrorCell(data = error, onRetryClickListener = onRetryClickListener)
        } else {
            SampleErrorCell(data = error, onRetryClickListener = onRetryClickListener)
        }
    }
}