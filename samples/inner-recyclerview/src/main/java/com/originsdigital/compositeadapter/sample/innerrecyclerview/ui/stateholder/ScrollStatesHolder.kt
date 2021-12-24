package com.originsdigital.compositeadapter.sample.innerrecyclerview.ui.stateholder

import androidx.recyclerview.widget.RecyclerView

class ScrollStatesHolder {

    private val stateHolders = hashMapOf<String, ScrollStateHolder>()

    fun setupRecyclerView(uniqueId: String, recyclerView: RecyclerView) {
        getStateHolder(uniqueId).setupRecyclerView(recyclerView)
    }

    fun onRecycled(uniqueId: String, recyclerView: RecyclerView) {
        getStateHolder(uniqueId).onRecycled(recyclerView)
    }

    private fun getStateHolder(uniqueId: String): ScrollStateHolder {
        return stateHolders[uniqueId] ?: ScrollStateHolder().also { stateHolder ->
            stateHolders[uniqueId] = stateHolder
        }
    }
}