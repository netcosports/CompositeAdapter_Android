package com.originsdigital.composite.adapter.sample.ui.sample.vm

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.originsdigital.composite.adapter.cell.Cell
import com.originsdigital.composite.adapter.sample.domain.entity.MessageEntity
import com.originsdigital.composite.adapter.sample.ui.di.SampleDI
import com.originsdigital.composite.adapter.sample.ui.sample.SampleMapper
import kotlinx.coroutines.*
import java.util.*

class SampleViewModel : ViewModel() {

    val state: SampleState = SampleState()

    private val app: Application = SampleDI.provideApp()
    private var toast: Toast? = null
    private val mapper = SampleMapper(
        app = app,
        itemsPerPage = ITEMS_PER_PAGE,
        viewMessageClickListener = {
            toast?.cancel()
            toast = Toast.makeText(app, "viewMessageClickListener", Toast.LENGTH_SHORT).also { it.show() }
        },
        viewBindingMessageClickListener = { click ->
            changeMessageType(click.item)
        },
        dataBindingMessageClickListener = { click ->
            changeMessageType(click.item)
        }
    )

    private var nextPage: Int = FIRST_PAGE
    private var isPaginationByScrollAllowed = true

    private var loadJob: Job? = null
    private var clickJob: Job? = null

    init {
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
                cellsOnLoading = mapper.mapToCells(
                    messages = messagesOnLoading,
                    extraCell = mapper.getLoaderCell(fullscreen = messagesOnLoading.isEmpty())
                )
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
                    newCells = mapper.mapToCells(newMessages)
                } else {
                    newMessages = state.messages
                    newCells = mapper.mapToCells(
                        messages = newMessages,
                        extraCell = mapper.getErrorCell(
                            mapper.getErrorMessage(random),
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
                mapper.changeMessageType(state.messages, input)
            }
            state.setData(newMessages, mapper.mapToCells(newMessages))
        }
    }

    private fun getNextPage(currentPage: Int): Int = currentPage + 1

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