package com.netcosports.composite.adapter.sample.ui.sample.vm

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.netcosports.composite.adapter.cell.Cell
import com.netcosports.composite.adapter.cell.ClickItem
import com.netcosports.composite.adapter.decoration.SpaceItemDecoration
import com.netcosports.composite.adapter.sample.domain.entity.MessageEntity
import com.netcosports.composite.adapter.sample.ui.di.SampleDI
import com.netcosports.composite.adapter.sample.ui.sample.cell.common.SampleErrorCell
import com.netcosports.composite.adapter.sample.ui.sample.cell.common.SampleFullScreenErrorCell
import com.netcosports.composite.adapter.sample.ui.sample.cell.common.SampleFullScreenLoaderCell
import com.netcosports.composite.adapter.sample.ui.sample.cell.common.SampleLoaderCell
import com.netcosports.composite.adapter.sample.ui.sample.cell.databinding.SampleDataBindingMessageCell
import com.netcosports.composite.adapter.sample.ui.sample.cell.view.SampleViewMessageCell
import com.netcosports.composite.adapter.sample.ui.sample.cell.viewbinding.SampleViewBindingMessageCell
import kotlinx.coroutines.*
import java.util.*

class SampleViewModel : ViewModel() {

    val state: SampleState = SampleState()
    private var nextPage: Int = FIRST_PAGE
    private var isPaginationByScrollAllowed = true

    private val firstItemDecoration: SpaceItemDecoration<Cell<*>>
    private val middleSmallItemDecoration: SpaceItemDecoration<Cell<*>>
    private val middleBigItemDecoration: SpaceItemDecoration<Cell<*>>
    private val lastItemDecoration: SpaceItemDecoration<Cell<*>>

    private var toast: Toast? = null
    private val viewMessageClickListener: (ClickItem<MessageEntity>) -> Unit = {
        toast?.cancel()
        toast = Toast.makeText(SampleDI.provideApp(), "viewMessageClickListener", Toast.LENGTH_SHORT).also { it.show() }
    }
    private val viewBindingMessageClickListener: (ClickItem<MessageEntity>) -> Unit = {
        changeMessageType(it.item)
    }
    private val dataBindingMessageClickListener: (ClickItem<MessageEntity>) -> Unit = {
        changeMessageType(it.item)
    }
    private var loadJob: Job? = null
    private var clickJob: Job? = null

    init {
        val app = SampleDI.provideApp()
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
        loadData(nextPage)
    }

    fun onRefresh() {
        loadData(FIRST_PAGE)
    }

    fun onLastItemVisibleChanged(position: Int) {
        val currentItemsSize = state.cellsData.value.orEmpty().size
        isPaginationByScrollAllowed = position < currentItemsSize - 1
        if (isPaginationByScrollAllowed && position > currentItemsSize - ITEMS_PER_PAGE / 2) {
            if (loadJob?.isActive != true) {
                isPaginationByScrollAllowed = false
                loadData(nextPage)
            }
        }
    }

    private fun loadData(page: Int) {
        loadJob?.cancel()
        if (page >= LAST_PAGE) {
            state.setRefreshing(false)
            return
        }
        loadJob = viewModelScope.launch {
            val isReload = page == FIRST_PAGE
            val messagesOnLoading: List<MessageEntity>
            val cellsOnLoading: List<Cell<*>>
            withContext(Dispatchers.IO) {
                messagesOnLoading = if (isReload) {
                    emptyList()
                } else {
                    state.messages
                }
                cellsOnLoading = mapToCells(messagesOnLoading, getLoaderCell(fullscreen = messagesOnLoading.isEmpty()))
            }
            state.setData(messagesOnLoading, cellsOnLoading)

            val newMessages: List<MessageEntity>
            val newCells: List<Cell<*>>
            withContext(Dispatchers.IO) {
                val random = Random()
                delay(3000)
                val isSuccess = random.nextInt(100) < 75

                if (isSuccess) {
                    nextPage = getNextPage(page)
                    isPaginationByScrollAllowed = true
                    val messages = (0..ITEMS_PER_PAGE).map { index ->
                        val id = page * ITEMS_PER_PAGE + index
                        val type = when (random.nextInt(3)) {
                            0 -> MessageEntity.Type.VIEW
                            1 -> MessageEntity.Type.VIEW_BINDING
                            else -> MessageEntity.Type.DATA_BINDING
                        }
                        MessageEntity(id = id.toString(), type = type, text = "Item $id")
                    }
                    newMessages = if (isReload) {
                        messages
                    } else {
                        state.messages + messages
                    }
                    newCells = mapToCells(newMessages)
                } else {
                    newMessages = state.messages
                    newCells = mapToCells(
                        newMessages,
                        getErrorCell(
                            getErrorMessage(random),
                            fullscreen = newMessages.isEmpty(),
                            onRetryClickListener = { loadData(page) }
                        )
                    )
                }
            }
            state.setData(newMessages, newCells)
        }
    }

    private fun changeMessageType(input: MessageEntity) {
        clickJob?.cancel()
        clickJob = viewModelScope.launch {
            val newMessages = withContext(Dispatchers.IO) {
                state.messages.map { message ->
                    if (input.id == message.id) {
                        message.copy(type = MessageEntity.Type.values().toList().minus(input.type).random())
                    } else {
                        message
                    }
                }
            }
            state.setData(newMessages, mapToCells(newMessages))
        }
    }

    private fun mapToCells(messages: List<MessageEntity>, extraCell: Cell<*>? = null): List<Cell<*>> {
        val result = mutableListOf<Cell<*>>()

        messages.mapIndexedTo(result) { i, message ->
            val decoration = when {
                i == 0 -> firstItemDecoration
                i == messages.size - 1 -> lastItemDecoration
                else -> {
                    if (i % ((ITEMS_PER_PAGE + messages.size) / ITEMS_PER_PAGE) == 0) {
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

    private fun getErrorMessage(random: Random): String {
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

    private fun getNextPage(currentPage: Int): Int = currentPage + 1

    private fun getLoaderCell(fullscreen: Boolean): Cell<*> {
        return if (fullscreen) {
            SampleFullScreenLoaderCell()
        } else {
            SampleLoaderCell()
        }
    }

    private fun getErrorCell(error: String, fullscreen: Boolean, onRetryClickListener: () -> Unit): Cell<*> {
        return if (fullscreen) {
            SampleFullScreenErrorCell(data = error, onRetryClickListener = onRetryClickListener)
        } else {
            SampleErrorCell(data = error, onRetryClickListener = onRetryClickListener)
        }
    }

    override fun onCleared() {
        super.onCleared()
        clickJob?.cancel()
        loadJob?.cancel()
        toast?.cancel()
        toast = null
    }

    companion object {
        private const val FIRST_PAGE = 0
        private const val LAST_PAGE = 3
        private const val ITEMS_PER_PAGE = 10
    }
}