package com.originsdigital.compositeadapter.sample.ui.sample.vm

import androidx.lifecycle.MutableLiveData
import com.originsdigital.compositeadapter.cell.Cell
import com.originsdigital.compositeadapter.sample.domain.entity.MessageEntity

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