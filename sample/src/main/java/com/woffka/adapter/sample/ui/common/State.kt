package com.woffka.adapter.sample.ui.common

import androidx.lifecycle.MutableLiveData
import com.netcosports.components.adapter.recycler.cell.Cell
import com.woffka.adapter.sample.domain.entity.MessageEntity

class State {

    var messages: List<MessageEntity> = emptyList()
    val cellsData: MutableLiveData<List<Cell<*>>> = MutableLiveData(emptyList())
    val refreshingData: MutableLiveData<Boolean> = MutableLiveData(false)

    fun setData(messages: List<MessageEntity>, cells: List<Cell<*>>) {
        this.messages = messages
        this.cellsData.value = cells
        setRefreshing(false)
    }

    fun setRefreshing(isRefreshing: Boolean) {
        refreshingData.value = isRefreshing
    }
}