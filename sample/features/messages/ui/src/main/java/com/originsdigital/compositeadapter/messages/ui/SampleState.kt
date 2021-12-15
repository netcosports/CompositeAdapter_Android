package com.originsdigital.compositeadapter.messages.ui

import androidx.lifecycle.MutableLiveData
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.messages.core.entity.MessageEntity

class SampleState {

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