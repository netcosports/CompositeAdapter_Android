package com.netcosports.composite.adapter.sample.ui.sample.vm

import androidx.lifecycle.MutableLiveData
import com.netcosports.composite.adapter.cell.Cell
import com.netcosports.composite.adapter.sample.domain.entity.MessageEntity

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